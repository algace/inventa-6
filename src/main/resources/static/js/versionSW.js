/*!
 * Funciones propias de version.html
 */

const ID_BOTON_BORRAR_APLICACION = '#botonBorrarAplicacion';
const ID_BOTON_ACEPTAR_SELECCIONAR_APLICACION = '#botonAceptarSeleccionarAplicacion';
const ID_TABLA_APLICACIONES = '#tablaAplicaciones';

// INICIO - Configuración de la tabla Aplicacion
var tabla_aplicaciones = $(ID_TABLA_APLICACIONES).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ipl><"clear">',
	searching:  false,
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
 });

//Campo para el filtro
$('#tablaAplicaciones tfoot th').each(function() {
    var foot = $('#tablaAplicaciones tfoot th').eq($(this).index()).text();
    $(this).html('<input type="text" class="form-control" placeholder="Filtrar por ' + foot + '" />');
});
  
// Funcionalidad del filtro
$("#tablaAplicaciones tfoot input").on('keyup change', function() {
	tabla_aplicaciones.column($(this).parent().index() + ':visible')
    	.search(this.value)
        .draw();
});
// FIN - Configuración de la tabla Aplicacion

$("#search").on('keyup change', function() {
    tabla_aplicaciones.columns()
    	.data()
    	.search(this.value)
        .draw();
});
