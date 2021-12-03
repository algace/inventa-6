/*!
 * Funciones propias de version.html
 */

const ID_TABLA_APLICACIONES = '#tablaAplicaciones';

// INICIO - Configuración de la tabla Aplicacion
var tabla_equipamiento = $(ID_TABLA_APLICACIONES).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ifpl><"clear">',
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
	tabla_equipamiento.column($(this).parent().index() + ':visible')
    	.search(this.value)
        .draw();
});
// FIN - Configuración de la tabla Aplicacion

const ID_BOTON_ACEPTAR_SELECCIONAR_APLICACION = '#botonAceptarSeleccionarAplicacion';
const ID_BOTON_BORRAR_EQUIPAMIENTO = '#botonBorrarAplicacion';
const ID_TABLA_SELECCIONAR_APLICACION = '#tablaSeleccionarAplicaciones';

// Configuración de la tabla del popup para seleccionar Versiones
$(ID_TABLA_SELECCIONAR_APLICACION).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ifpl><"clear">',
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
 })
 .on('select', function() {
     $(ID_BOTON_ACEPTAR_SELECCIONAR_APLICACION).removeAttr('disabled');
 })
 .on('deselect', function() {
     $(ID_BOTON_ACEPTAR_SELECCIONAR_APLICACION).attr('disabled', 'disabled');
 }) 
 .on('click', 'tr', function() {
	 rowNode = this;
	 idRow = this
});

// Botón Borrar
$(ID_BOTON_BORRAR_APLICACION).on('click', function () {
	
	// 1. Eliminamos la fila de la tabla de versiones
	$(ID_TABLA_APLICACIONES).DataTable()
		.row(idRow)
		.remove()
		.draw();
	
	// 2. Insertamos la fila eliminada, en la tabla de versiones del popup
	$(ID_TABLA_SELECCIONAR_APLICACION).DataTable()
		.row
		.add(rowNode)
		.draw();
		
	
	// 3. Deseleccionamos todas las filas de la tabla de versiones del popup
	$(ID_TABLA_SELECCIONAR_APLICACION).DataTable().rows().deselect();
	
	// 4. Deshabilitamos el botón borrar
	$(ID_BOTON_BORRAR_APLICACION).attr('disabled', 'disabled');
});

// Botón Aceptar del popup
$(ID_BOTON_ACEPTAR_SELECCIONAR_APLICACION).on('click', function () {

	// 1. Eliminamos la fila de la tabla de versiones del popup
	$(ID_TABLA_SELECCIONAR_APLICACION).DataTable()
		.row(idRow)
		.remove()
		.draw();
		
	
	addElementAplicacionToRow();

	// 2. Insertamos la fila eliminada, en la tabla de versiones
	$(ID_TABLA_APLICACIONES).DataTable()
		.row
		.add(rowNode)
		.draw();
	
	// 3. Deseleccionamos todas las filas de la tabla de versiones
	$(ID_TABLA_APLICACIONES).DataTable().rows().deselect();
	
	// 4. Deshabilitamos el botón aceptar
	$(ID_TABLA_SELECCIONAR_APLICACION).attr('disabled', 'disabled');
});

function addElementAplicacionToRow(){
	
	var equipamientos = $("[name='aplicacionesSW[]']");
	var tamList = equipamientos.length;
	var numChildren = rowNode.children.length;
	var idAplicacion = rowNode.children[numChildren-1].value;
	var input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("id", "aplicacionesSW" + tamList + ".id");
	input.setAttribute("name", "aplicacionesSW[" + tamList + "].id");
	input.setAttribute("value", idAplicacion);
	
	rowNode.children[numChildren-1].remove();
	rowNode.append(input);
};
