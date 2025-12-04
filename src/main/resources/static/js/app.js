// Archivo JS mínimo para futuras interacciones del frontend
document.addEventListener('DOMContentLoaded', function () {
    // Manejar búsqueda rápida de envío por ID desde la navbar
    const buscarForm = document.getElementById('buscarEnvioForm');
    const buscarInput = document.getElementById('buscarEnvioInput');
    const buscarBtn = document.getElementById('buscarEnvioBtn');
    if (buscarForm && buscarInput && buscarBtn) {
        const doSearch = () => {
            try {
                const val = buscarInput.value && buscarInput.value.trim();
                console.log('[app] ejecutar búsqueda:', val);
                if (!val) return;
                // redirige a la página de búsqueda de envíos (query param)
                window.location.href = `/envios/buscar?q=${encodeURIComponent(val)}`;
            } catch (err) {
                console.error('[app] error en búsqueda:', err);
            }
        };

        // Click en el botón
        buscarBtn.addEventListener('click', (e) => { e.preventDefault(); doSearch(); });

        // Submit del formulario (por Enter) — prevenir y usar la función
        buscarForm.addEventListener('submit', (e) => { e.preventDefault(); doSearch(); });

        // También interceptar Enter en el input para mayor compatibilidad
        buscarInput.addEventListener('keydown', (e) => { if (e.key === 'Enter') { e.preventDefault(); doSearch(); } });
    } else {
        console.log('[app] elementos de búsqueda no encontrados en DOM');
    }

    // Rellenar origen/destino en el formulario de envíos cuando se selecciona una ruta
    const rutaSelect = document.getElementById('envioRutaSelect');
    const origenInput = document.getElementById('envioOrigenInput');
    const destinoInput = document.getElementById('envioDestinoInput');
    const rutaIdInput = document.getElementById('envioRutaIdInput');
    if (rutaSelect && origenInput && destinoInput) {
        rutaSelect.addEventListener('change', function () {
            const sel = rutaSelect.options[rutaSelect.selectedIndex];
            const origen = sel.getAttribute('data-origen');
            const destino = sel.getAttribute('data-destino');
            const rid = sel.value;
            if (origen) origenInput.value = origen;
            if (destino) destinoInput.value = destino;
            if (rutaIdInput) rutaIdInput.value = rid;
        });
    }
});
