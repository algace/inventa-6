/*!
 * Funciones propias de usuario.html
 */

var rowNode;

const ID_TABLA_SECTORES_ATC = '#tablaSectoresATC';
const ID_INPUT_SEARCH_SECTORES_ATC = '#searchSectoresATC';

// INICIO - Configuración de la tabla Aplicacion
var tabla_sectores_atc = $(ID_TABLA_SECTORES_ATC).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ipl><"clear">',
	searching:  true,
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


// FIN - Configuración de la tabla Aplicacion

$(ID_INPUT_SEARCH_SECTORES_ATC).on('keyup change', function() {
    tabla_sectores_atc.columns()
    	.data()
    	.search(this.value)
        .draw();
});
