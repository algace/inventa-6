/*!
 * Funciones propias de version.html
 */

const ID_TABLA_TIPOS_SISTEMAS = '#tablaTiposSistemas';
const ID_BOTON_BORRAR_SISTEMA = '#enlaceBorrarSistema';
const ID_BOTON_ACEPTAR_SELECCIONAR_SISTEMA = '#botonAceptarSeleccionarSistema';
const ID_MODAL_SISTEMAS = '#popupSeleccionarSistema';
const ID_INPUT_HIDDEN_ID_SISTEMA = 'tipoSistema.id';
const ID_INPUT_NOMBRE_SISTEMA = 'tipoSistema.nombre';

var rowElement = null;
var idElement  = null;
var rowNode = null;

// INICIO - Configuración de la tabla Aplicacion
var tabla_tipos_sistemas = $(ID_TABLA_TIPOS_SISTEMAS).DataTable({ 
	select: 'single',
	dom: '<"top">rt<"bottom"ipl><"clear">',
	searching:  true,
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
    $(ID_BOTON_ACEPTAR_SELECCIONAR_SISTEMA).removeAttr('disabled');
 })
 .on('deselect', function() {
    $(ID_BOTON_ACEPTAR_SELECCIONAR_SISTEMA).attr('disabled', 'disabled');
 })
 .on('click', 'tr', function() {
	 rowNode = this;
	 rowElement = tabla_tipos_sistemas.row(this).data();
	 idElement = tabla_tipos_sistemas.row(this).data()[0];
});

// Botón Borrar
$(ID_BOTON_BORRAR_SISTEMA).on('click', function () {
	
	document.getElementById(ID_INPUT_HIDDEN_ID_SISTEMA).value = "";
	document.getElementById(ID_INPUT_NOMBRE_SISTEMA).value = "";
	
});

// Botón Aceptar del popup
$(ID_BOTON_ACEPTAR_SELECCIONAR_SISTEMA).on('click', function () {

	document.getElementById(ID_INPUT_HIDDEN_ID_SISTEMA).value = rowElement[0];
	document.getElementById(ID_INPUT_NOMBRE_SISTEMA).value = rowElement[1];
	
});

$(ID_MODAL_SISTEMAS).on('show.bs.modal', function () {
	$(ID_TABLA_TIPOS_SISTEMAS).DataTable().rows().deselect();
	$(ID_BOTON_ACEPTAR_SELECCIONAR_SISTEMA).attr('disabled', 'disabled');
})