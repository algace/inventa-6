const ID_DATETIMEPICKER_FECHA = '#datetimepickerFechaPublicacion';


// INICIO - Configuración para los campos de fecha y hora
$(ID_DATETIMEPICKER_FECHA).datetimepicker({
	widgetPositioning: {
	    horizontal: 'right',
	    vertical: 'bottom'
	},
	locale: 'es',
	format: 'YYYY-MM-DD'
});