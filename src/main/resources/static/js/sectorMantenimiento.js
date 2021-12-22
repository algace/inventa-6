/*!
 * Funciones propias de version.html
 */

const ID_TABLA_REGIONES_MANTENIMIENTO = '#tablaRegionesMantenimiento';
const ID_BOTON_BORRAR_REGION = '#enlaceBorrarRegion';
const ID_BOTON_ACEPTAR_SELECCIONAR_REGION = '#botonAceptarSeleccionarRegion';
const ID_MODAL_REGIONES = '#popupSeleccionarRegion';
const ID_INPUT_HIDDEN_ID_REGION = 'regionMantenimiento.id';
const ID_INPUT_NOMBRE_REGION = 'regionMantenimiento.nombre';

var rowElement = null;
var idElement  = null;
var rowNode = null;

// INICIO - Configuración de la tabla de regiones de mantenimiento
var tabla_regiones_mantenimiento = $(ID_TABLA_REGIONES_MANTENIMIENTO).DataTable({ 
	select: 'single',
	dom: '<"top">rt<"bottom"ipl><"clear">',
	searching:  false,
	columnDefs: [{ 
		targets: 0,
		visible: false
    }],
	language: {
	    'sProcessing':     'Procesando...',
	    'sLengthMenu':     'Mostrar _MENU_ registros',
	    'sZeroRecords':    'No se encontraron resultados',
	    'sEmptyTable':     'Ningún dato disponible en esta tabla',
	    'sInfo':           'Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros',
	    'sInfoEmpty':      'Mostrando registros del 0 al 0 de un total de 0 registros',
	    'sInfoFiltered':   '(filtrado de un total de _MAX_ registros)',
	    'sInfoPostFix':    '',
	    'sSearch':         'Buscar:',
	    'sUrl':            '',
	    'sInfoThousands':  ',',
	    'sLoadingRecords': 'Cargando...',
	    'oPaginate': {
	        'sFirst':    '<<',
	        'sLast':     '>>',
	        'sNext':     '>',
	        'sPrevious': '<'
	    },
	    'oAria': {
	        'sSortAscending':  ': Activar para ordenar la columna de manera ascendente',
	        'sSortDescending': ': Activar para ordenar la columna de manera descendente'
	    },
	    select: {
            rows: ''
        }
	}
 })
  .on('select', function() {
    $(ID_BOTON_ACEPTAR_SELECCIONAR_REGION).removeAttr('disabled');
 })
 .on('deselect', function() {
    $(ID_BOTON_ACEPTAR_SELECCIONAR_REGION).attr('disabled', 'disabled');
 })
 .on('click', 'tr', function() {
	 rowNode = this;
	 rowElement = tabla_regiones_mantenimiento.row(this).data();
	 idElement = tabla_regiones_mantenimiento.row(this).data()[0];
});

// Botón Borrar
$(ID_BOTON_BORRAR_REGION).on('click', function () {
	
	document.getElementById(ID_INPUT_HIDDEN_ID_REGION).value = "";
	document.getElementById(ID_INPUT_NOMBRE_REGION).value = "";
	
});

// Botón Aceptar del popup
$(ID_BOTON_ACEPTAR_SELECCIONAR_REGION).on('click', function () {

	document.getElementById(ID_INPUT_HIDDEN_ID_REGION).value = rowElement[0];
	document.getElementById(ID_INPUT_NOMBRE_REGION).value = rowElement[1];
	
});

$(ID_MODAL_REGIONES).on('show.bs.modal', function () {
	$(ID_TABLA_REGIONES_MANTENIMIENTO).DataTable().rows().deselect();
	$(ID_BOTON_ACEPTAR_SELECCIONAR_REGION).attr('disabled', 'disabled');
})