/*!
 * Funciones propias de aplicacion.html
 */

const ID_BOTON_BORRAR_VERSION = '#botonBorrarVersion';
const ID_BOTON_ACEPTAR_SELECCIONAR_VERSION = '#botonAceptarSeleccionarVersion';
const ID_TABLA_EQUIPAMIENTOS = '#tablaEquipamiento';
const ID_TABLA_VERSIONES = '#tablaVersiones';
const ID_TABLA_SELECCIONAR_VERSION = '#tablaSeleccionarVersion';
const ID_DATETIMEPICKER_FECHA = '#datetimepickerFecha';
const ID_DATETIMEPICKER_HORA = '#datetimepickerHora';

var rowElement = null;
var idElement  = null;
var rowNode = null;

// INICIO - Configuración para los campos de fecha y hora
$(ID_DATETIMEPICKER_FECHA).datetimepicker({
	widgetPositioning: {
	    horizontal: 'right',
	    vertical: 'bottom'
	},
	locale: 'es',
	format: 'YYYY-MM-DD'
});
$(ID_DATETIMEPICKER_HORA).datetimepicker({
	widgetPositioning: {
	    horizontal: 'right',
	    vertical: 'top'
	},
    format: 'HH:mm'
});
// FIN - Configuración para los campos de fecha y hora

// INICIO - Configuración de la tabla Versiones
var tabla_versiones = $(ID_TABLA_VERSIONES).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ifpl><"clear">',
	searching:  false,
	data: JSON.parse(versionesSWJson),
	columnDefs: [{ 
		targets: 0,
		visible: false
    },{ 
		targets: 1,
        render: function(data, type, full, meta){
		   if (type == "display"){
			   var valueId = idElement != null ? idElement : full.id;
	           return data + '<input type="hidden" id="versionesSW' + valueId +'.id" name="versionesSW['+ valueId + '].id" value="' + valueId + '">';
           }else{
			   return data;
		   }
        }
    }],
	columns: [
	  {data: "id"},
	  {data: "nombre", name: "nombre", title: "Nombre"}, 
	  {data: "descripcion", name: "descripcion", title: "Descripción"}
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
     $(ID_BOTON_BORRAR_VERSION).removeAttr('disabled');
 })
 .on('deselect', function() {
     $(ID_BOTON_BORRAR_VERSION).attr('disabled', 'disabled');
 })
 .on('click', 'tr', function() {
	 rowNode = this;
	 rowElement = tabla_versiones.row(this).data();
	 idElement = tabla_versiones.row(this).data()[0]
});

// Campo para el filtro
$('#tablaVersiones tfoot th').each(function() {
    var foot = $('#tablaVersiones tfoot th').eq($(this).index()).text();
    $(this).html('<input type="text" class="form-control" placeholder="Filtrar por ' + foot + '" />');
});
  
// Funcionalidad del filtro
$("#tablaVersiones tfoot input").on('keyup change', function() {
	tabla_versiones.column($(this).parent().index() + ':visible')
    	.search(this.value)
        .draw();
});
// FIN - Configuración de la tabla Versiones
 
// Configuración de la tabla del popup para seleccionar Versiones
var tabla_seleccionar_versiones =  $(ID_TABLA_SELECCIONAR_VERSION).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ifpl><"clear">',
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
     $(ID_BOTON_ACEPTAR_SELECCIONAR_VERSION).removeAttr('disabled');
 })
 .on('deselect', function() {
     $(ID_BOTON_ACEPTAR_SELECCIONAR_VERSION).attr('disabled', 'disabled');
 }) 
 .on('click', 'tr', function() {
	 rowNode = this;
	 rowElement = tabla_seleccionar_versiones.row(this).data();
	 idElement = tabla_seleccionar_versiones.row(this).data()[0];
});

// Botón Borrar
$(ID_BOTON_BORRAR_VERSION).on('click', function () {
	
	// 1. Eliminamos la fila de la tabla de versiones
	$(ID_TABLA_VERSIONES).DataTable()
	.row(rowNode)
	.remove()
	.draw();

	// 2. Insertamos la fila eliminada, en la tabla de versiones del popup
	$(ID_TABLA_SELECCIONAR_VERSION).DataTable()
		.row
		.add([rowElement.id, 
		  	  rowElement.nombre, 
		  	  rowElement.descripcion
		])
		.draw();
	
	// 3. Deseleccionamos todas las filas de la tabla de versiones del popup
	$(ID_TABLA_SELECCIONAR_VERSION).DataTable().rows().deselect();
	
});

// Botón Aceptar del popup
$(ID_BOTON_ACEPTAR_SELECCIONAR_VERSION).on('click', function () {

	// 1. Eliminamos la fila de la tabla de versiones del popup
	$(ID_TABLA_SELECCIONAR_VERSION).DataTable()
		.row(rowNode)
		.remove()
		.draw();
	
	// 2. Insertamos la fila eliminada, en la tabla de versiones
	$(ID_TABLA_VERSIONES).DataTable()
		.row
		.add({id: rowElement[0], 
			nombre: rowElement[1], 
			descripcion: rowElement[2]})
		.draw();
	
	// 3. Deseleccionamos todas las filas de la tabla de versiones
	$(ID_TABLA_VERSIONES).DataTable().rows().deselect();
	
	// 4. Deshabilitamos el botón aceptar
	$(ID_TABLA_SELECCIONAR_VERSION).attr('disabled', 'disabled');
});

// INICIO - Configuración de la tabla Equipamiento
var tabla_equipamiento = $(ID_TABLA_EQUIPAMIENTOS).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ifpl><"clear">',
	searching:  false,
	data: JSON.parse(equipamientosJson),
	columnDefs: [{ 
		targets: 0,
		visible: false
    },{ 
		targets: 1,
        render: function(data, type, full, meta){
		   if (type == "display"){
			   var valueId = idElement != null ? idElement : full.id;
	           return data + '<input type="hidden" id="equipamientos' + valueId +'.id" name="equipamientos['+ valueId + '].id" value="' + valueId + '">';
           }else{
			   return data;
		   }
        }
    }],
	columns: [
			  {data: "id"},
			  {data: "nombre", name: "nombre", title: "Nombre"}, 
			  {data: "marca", name: "marca", title: "Marca"}, 
			  {data: "modelo", name: "modelo", title: "Modelo"},
			  {data: "entradas", name: "entradas", title: "Entradas"},
			  {data: "salidas", name: "salidas", title: "Salidas"}, 
			  {data: "ganancia", name: "ganancia", title: "Ganancia"}, 
			  {data: "perdida", name: "perdida", title: "Perdida"}],
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
     
 })
 .on('deselect', function() {
     
 })
 .on('click', 'tr', function() {
	 rowNode = this;
	 rowElement = tabla_equipamiento.row(this).data();
	 idElement = tabla_equipamiento.row(this).data()[0];
	 
});

//Campo para el filtro
$('#tablaEquipamiento tfoot th').each(function() {
    var foot = $('#tablaEquipamiento tfoot th').eq($(this).index()).text();
    $(this).html('<input type="text" class="form-control" placeholder="Filtrar por ' + foot + '" />');
});
  
// Funcionalidad del filtro
$("#tablaEquipamiento tfoot input").on('keyup change', function() {
	tabla_equipamiento.column($(this).parent().index() + ':visible')
    	.search(this.value)
        .draw();
});
// FIN - Configuración de la tabla Equipamiento

const ID_BOTON_ACEPTAR_SELECCIONAR_EQUIPAMIENTO = '#botonAceptarSeleccionarEquipamiento';
const ID_BOTON_BORRAR_EQUIPAMIENTO = '#botonBorrarEquipamiento';
const ID_TABLA_SELECCIONAR_EQUIPAMIENTO = '#tablaSeleccionarEquipamientos';

// Configuración de la tabla del popup para seleccionar Versiones
var tabla_seleccionar_equipamiento = $(ID_TABLA_SELECCIONAR_EQUIPAMIENTO).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ifpl><"clear">',
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
     $(ID_BOTON_ACEPTAR_SELECCIONAR_EQUIPAMIENTO).removeAttr('disabled');
 })
 .on('deselect', function() {
     $(ID_BOTON_ACEPTAR_SELECCIONAR_EQUIPAMIENTO).attr('disabled', 'disabled');
 }) 
 .on('click', 'tr', function() {
	 rowNode = this;
	 rowElement = tabla_seleccionar_equipamiento.row(this).data();
	 idElement = tabla_seleccionar_equipamiento.row(this).data()[0];
});

// Botón Borrar
$(ID_BOTON_BORRAR_EQUIPAMIENTO).on('click', function () {
	
	// 1. Eliminamos la fila de la tabla de versiones
	$(ID_TABLA_EQUIPAMIENTOS).DataTable()
		.row(rowNode)
		.remove()
		.draw();
	
	// 2. Insertamos la fila eliminada, en la tabla de versiones del popup
	$(ID_TABLA_SELECCIONAR_EQUIPAMIENTO).DataTable()
		.row
		.add([rowElement.id, 
			  rowElement.nombre, 
			  rowElement.marca,
			  rowElement.modelo,
			  rowElement.entradas,
			  rowElement.salidas,
			  rowElement.ganancia,
			  rowElement.perdida 
		])
		.draw();
		
		
	// 3. Deseleccionamos todas las filas de la tabla de versiones del popup
	$(ID_TABLA_SELECCIONAR_EQUIPAMIENTO).DataTable().rows().deselect();
	
	// 4. Deshabilitamos el botón borrar
	$(ID_BOTON_BORRAR_EQUIPAMIENTO).attr('disabled', 'disabled');
});

// Botón Aceptar del popup
$(ID_BOTON_ACEPTAR_SELECCIONAR_EQUIPAMIENTO).on('click', function () {

	// 1. Eliminamos la fila de la tabla de versiones del popup
	$(ID_TABLA_SELECCIONAR_EQUIPAMIENTO).DataTable()
		.row(rowNode)
		.remove()
		.draw();
		

	// 2. Insertamos la fila eliminada, en la tabla de versiones
	$(ID_TABLA_EQUIPAMIENTOS).DataTable()
		.row
		.add({id: rowElement[0], 
			nombre: rowElement[1], 
			marca: rowElement[2], 
			modelo: rowElement[3], 
			entradas: rowElement[4],
			salidas: rowElement[5],
			ganancia: rowElement[6],
			perdida: rowElement[7]})
		.draw();
	
	// 3. Deseleccionamos todas las filas de la tabla de versiones
	$(ID_TABLA_EQUIPAMIENTOS).DataTable().rows().deselect();
	
	// 4. Deshabilitamos el botón aceptar
	$(ID_TABLA_SELECCIONAR_EQUIPAMIENTO).attr('disabled', 'disabled');
});