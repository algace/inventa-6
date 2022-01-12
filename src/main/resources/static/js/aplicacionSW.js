/*!
 * Funciones propias de aplicacion.html
 */
const ID_APLICACION = '#id';
const ID_BOTON_BORRAR_VERSION = '#botonBorrarVersion';
const ID_BOTON_ACEPTAR_SELECCIONAR_VERSION = '#botonAceptarSeleccionarVersion';
const ID_TABLA_EQUIPAMIENTOS = '#tablaEquipamiento';
const ID_TABLA_VERSIONES = '#tablaVersiones';
const ID_TABLA_SELECCIONAR_VERSION = '#tablaSeleccionarVersion';
const ID_DATETIMEPICKER_FECHA = '#datetimepickerFecha';
const ID_DATETIMEPICKER_HORA = '#datetimepickerHora';
const ID_BOTON_ACEPTAR_SELECCIONAR_EQUIPAMIENTO = '#botonAceptarSeleccionarEquipamiento';
const ID_BOTON_BORRAR_EQUIPAMIENTO = '#botonBorrarEquipamiento';
const ID_TABLA_SELECCIONAR_EQUIPAMIENTO = '#tablaSeleccionarEquipamientos';
const ID_MODAL_VERSION = "#popupSeleccionarVersion";
const ID_MODAL_EQUIPAMIENTO = "#popupSeleccionarEquipamiento";
const ID_INPUT_SEARCH_VERSIONES = "#searchVersiones";
const ID_INPUT_SEARCH_EQUIPAMIENTOS = "#searchEquipamientos";
const ID_INPUT_SEARCH_SELECCIONAR_VERSIONES = "#searchSeleccionarVersiones";
const ID_INPUT_SEARCH_SELECCIONAR_EQUIPAMIENTOS = "#searchSeleccionarEquipamientos";



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
	dom: '<"top">rt<"bottom"ipl><"clear">',
	data: JSON.parse(versionesSWJson),
	searching:  true,
	columnDefs: [{ 
		targets: 0,
		visible: false
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
	deleteVersion($(ID_APLICACION).val(), rowElement.id);
});

// Botón Aceptar del popup
$(ID_BOTON_ACEPTAR_SELECCIONAR_VERSION).on('click', function () {
	insertVersion($(ID_APLICACION).val(), rowElement[0]);
});

$(ID_MODAL_VERSION).on('show.bs.modal', function () {
	$(ID_BOTON_ACEPTAR_SELECCIONAR_VERSION).attr('disabled', 'disabled');
})

function addVersionToAplicacionTable(){
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
}

function insertVersion(idAplicacionSW, idVersionSW){

    $.ajax({
        type: 'POST',
        url: urlInsertVersion + idAplicacionSW + "/" + idVersionSW,
        data: JSON.stringify({}),
        dataType: 'json',
        contentType: 'application/json',
        cache: false,
        processData:false,
        success: function(response) { 
			addVersionToAplicacionTable();   
        },
        error: function(e) {
			alert(e); 
        }
    });
}

function deleteVersionToAplicacionTable(){
	
		if (tabla_versiones.rows('.selected').any()){
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
	}
}

function deleteVersion(idAplicacionSW, idVersionSW){

    $.ajax({
        type: 'DELETE',
        url: urlDeleteVersion + idAplicacionSW + "/" + idVersionSW,
        data: JSON.stringify({}),
        dataType: 'json',
        contentType: 'application/json',
        cache: false,
        processData:false,
        success: function(response) { 
			deleteVersionToAplicacionTable();     
        },
        error: function(e) {
			alert(e); 
        }
    });
}

	

// INICIO - Configuración de la tabla Equipamiento
var tabla_equipamiento = $(ID_TABLA_EQUIPAMIENTOS).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ipl><"clear">',
	data: JSON.parse(equipamientosJson),
	searching:  true,
	columnDefs: [{ 
		targets: 0,
		visible: false
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

// Configuración de la tabla del popup para seleccionar Versiones
var tabla_seleccionar_equipamiento = $(ID_TABLA_SELECCIONAR_EQUIPAMIENTO).DataTable({
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
	deleteEquipamiento($(ID_APLICACION).val(), rowElement.id);
});

// Botón Aceptar del popup
$(ID_BOTON_ACEPTAR_SELECCIONAR_EQUIPAMIENTO).on('click', function () {
	insertarEquipamiento($(ID_APLICACION).val(), rowElement[0]);
});

function addEquipamientoToAplicacionTable(){
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
}

function insertarEquipamiento(idAplicacionSW, idEquipamiento){

    $.ajax({
        type: 'POST',
        url: urlInsertEquipamiento + idAplicacionSW + "/" + idEquipamiento,
        data: JSON.stringify({}),
        dataType: 'json',
        contentType: 'application/json',
        cache: false,
        processData:false,
        success: function(response) { 
			addEquipamientoToAplicacionTable();   
        },
        error: function(e) {
			alert(e); 
        }
    });
}

function deleteEquipamientoToAplicacionTable(){
	
	if(tabla_equipamiento.rows('.selected').any()){
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
	}
}

function deleteEquipamiento(idAplicacionSW, idEquipamiento){

    $.ajax({
        type: 'DELETE',
        url: urlDeleteEquipamiento + idAplicacionSW + "/" + idEquipamiento,
        data: JSON.stringify({}),
        dataType: 'json',
        contentType: 'application/json',
        cache: false,
        processData:false,
        success: function(response) { 
			deleteEquipamientoToAplicacionTable();    
        },
        error: function(e) {
			alert(e); 
        }
    });
}

$(ID_MODAL_EQUIPAMIENTO).on('show.bs.modal', function () {
	$(ID_BOTON_ACEPTAR_SELECCIONAR_EQUIPAMIENTO).attr('disabled', 'disabled');
});

$(ID_INPUT_SEARCH_EQUIPAMIENTOS).on('keyup change', function() {
    tabla_equipamiento.columns()
    	.data()
    	.search(this.value)
        .draw();
});

$(ID_INPUT_SEARCH_VERSIONES).on('keyup change', function() {
    tabla_versiones.columns()
    	.data()
    	.search(this.value)
        .draw();
});

$(ID_INPUT_SEARCH_SELECCIONAR_EQUIPAMIENTOS).on('keyup change', function() {
    tabla_seleccionar_equipamiento.columns()
    	.data()
    	.search(this.value)
        .draw();
});

$(ID_INPUT_SEARCH_SELECCIONAR_VERSIONES).on('keyup change', function() {
    tabla_seleccionar_versiones.columns()
    	.data()
    	.search(this.value)
        .draw();
});
