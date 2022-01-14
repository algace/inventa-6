/*!
 * Funciones propias de tipoSistema.html
 */

const ID_TABLA_TIPOS_SUBSISTEMAS = '#tablaTiposSubsistemas';
const ID_INPUT_SEARCH_SUBSISTEMA = '#searchSubsistemas';
const ID_INPUT_COLOR= '#color';
const ID_INPUT_COLOR_TEXTO= '#colorTexto';



// INICIO - Máscara para campo numérico

// INICIO - Configuración de la tabla Aplicacion
var tabla_tipos_subsistemas = $(ID_TABLA_TIPOS_SUBSISTEMAS).DataTable({
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
 
 $(ID_INPUT_SEARCH_SUBSISTEMA).on('keyup change', function() {
    tabla_tipos_subsistemas.columns()
    	.data()
    	.search(this.value)
        .draw();
});

$(ID_INPUT_COLOR).on('keyup change', function() {
 	$("#Ejemplo").css("background-color",$(ID_INPUT_COLOR).val());
});

$(ID_INPUT_COLOR_TEXTO).on('keyup change', function() {	
 	$("#Ejemplo").css("color",$(ID_INPUT_COLOR_TEXTO).val());
});

