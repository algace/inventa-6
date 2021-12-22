/*!
 * Funciones propias de las plantillas de listados
 */

const ID_TABLA_RESULTADOS = '#tablaResultados';
const ID_TABLA_RESULTADOS_SISTEMAS = '#tablaResultadosSistemas';
const ID_TABLA_RESULTADOS_CON_SPLIT = '#tablaResultadosConSplit';
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
    table_sistemas.column($(this).parent().index() + ':visible')
    	.search(this.value)
        .draw();
});

$("#search").on('keyup change', function() {
    table_sistemas.columns()
    	.data()
    	.search(this.value)
        .draw();
});

// INICIO - Configuración de la tabla
var table_sistemas = $(ID_TABLA_RESULTADOS_SISTEMAS).DataTable( {
	select: 'single',
	dom: '<"top">rt<"bottom"ipl><"clear">',
	searching:  true,
		columnDefs: [{ 
		targets: 3,
        render: function(data){
	       			return '<div style="background-color:' + data + ' !important; height: 18px; width: 18px;"></div>';
        		}
        },{ 
		targets: 4,
        render: function(data){
	       			return  '<div style="background-color:' + data + ' !important; height: 18px; width: 18px;"></div>';
        		}
        }
    ],
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
	if (table){
	    table.columns()
	    	.data()
	    	.search(this.value)
	        .draw();
    }else{
		if(table_con_split){
			table_con_split.columns()
		    	.data()
		    	.search(this.value)
		        .draw()
		}else{
		    table_sistemas.columns()
		    	.data()
		    	.search(this.value)
		        .draw()
	    }
    }
});


// Campo para el filtro
$('#tablaResultadosSistemas tfoot th').each(function() {
    var foot = $('#tablaResultadosSistemas tfoot th').eq($(this).index()).text();
    $(this).html('<input type="text" class="form-control form-control-sm" placeholder="Filtrar por ' + foot + '" />');
});
  
// Funcionalidad del filtro
$("#tablaResultadosSistemas tfoot input").on('keyup change', function() {
    table_sistemas.column($(this).parent().index() + ':visible')
    	.search(this.value)
        .draw();
});

// FIN - Configuración de la tabla
 
// INICIO - Configuración de la tabla
var table_con_split = $(ID_TABLA_RESULTADOS_CON_SPLIT).DataTable( {
	select: 'single',
	dom: '<"top">rt<"bottom"ipl><"clear">',
	searching:  true,
	columnDefs: [{ 
		targets: targetSplit,
        render: function(data, type, full, meta){
		   
		   if (type == "display"){
			   var result = "";
			   var list = data.split(":");
			   for (var i=0; i < list.length; i++) {
					if ((list.length - 1) == i){
						result = result + list[i];
					}else{
						result = result + list[i] + ", <br>";
					}
				}
	           return result;
           }else{
				return "";
			}
        }
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
$('#tablaResultadosConSplit tfoot th').each(function() {
    var foot = $('#tablaResultadosConSplit tfoot th').eq($(this).index()).text();
    $(this).html('<input type="text" class="form-control form-control-sm" placeholder="Filtrar por ' + foot + '" />');
});
  
// Funcionalidad del filtro
$("#tablaResultadosConSplit tfoot input").on('keyup change', function() {
    table_con_split.column($(this).parent().index() + ':visible')
    	.search(this.value)
        .draw();
});
