/*!
 * Funciones propias de las plantillas de listados
 */

const ID_TABLA_RESULTADOS = '#tablaResultados';
const ID_BOTON_BORRAR = '#botonBorrar';
const ID_BOTON_MODIFICAR = '#botonModificar';
const ID_URI_BORRAR = '#uriBorrar';
const ID_URI_MODIFICAR = '#uriModificar';
const ID_URI_LEER = '#uriLeer';

var idRow = 0;
var botonModificarEliminarEnable = false;

// Creación de la URI para modificar la fila seleccionada
$(ID_BOTON_MODIFICAR).on('click', function () {
	if(botonModificarEliminarEnable){
		location.href = $(ID_URI_MODIFICAR).attr('href').concat('/').concat(idRow);
	}
});

// Creación de la URI para eliminar la fila seleccionada
$(ID_BOTON_BORRAR).on('click', function () {
	if(botonModificarEliminarEnable){
		location.href = $(ID_URI_BORRAR).attr('href').concat('/').concat(idRow);
	}
});

// INICIO - Configuración de la tabla
var table = $(ID_TABLA_RESULTADOS).DataTable( {
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
 })
 .on('select', function() {
     botonModificarEliminarEnable = true;
 })
 .on('deselect', function() {
    botonModificarEliminarEnable = false;
 })
 .on('click', 'tr', function() {
	 idRow = this.id;
 })
 .on('dblclick', 'tr', function() {
	 if ('' != this.id) {		
		 location.href = $(ID_URI_LEER).attr('href').concat('/').concat(this.id);
	 }
});

// Campo para el filtro
$('#tablaResultados tfoot th').each(function() {
    var foot = $('#tablaResultados tfoot th').eq($(this).index()).text();
    $(this).html('<input type="text" class="form-control form-control-sm" placeholder="Filtrar por ' + foot + '" />');
});
  
// Funcionalidad del filtro
$("#tablaResultados tfoot input").on('keyup change', function() {
    table.column($(this).parent().index() + ':visible')
    	.search(this.value)
        .draw();
});

$("#search").on('keyup change', function() {
    table.columns()
    	.data()
    	.search(this.value)
        .draw();
});
// FIN - Configuración de la tabla
 

