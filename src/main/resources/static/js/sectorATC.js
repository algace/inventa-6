const ID_SECTOR_ATC = '#id';
const ID_DATETIMEPICKER_FECHA = '#datetimepickerFecha';
const ID_BOTON_BORRAR_AIRBLOCK = '#botonBorrarAirblock';
const ID_BOTON_ACEPTAR_SELECCIONAR_AIRBLOCK = '#botonAceptarSeleccionarAirblock';
const ID_TABLA_AIRBLOCKS = '#tablaAirblocks';
const ID_TABLA_SELECCIONAR_AIRBLOCK = '#tablaSeleccionarAirblock';
const ID_MODAL_AIRBLOCK = "#popupSeleccionarAirblock";
const ID_INPUT_SEARCH_AIRBLOCKS = "#searchAirblock";
const ID_INPUT_SEARCH_SELECCIONAR_AIRBLOCKS = "#searchSeleccionarAirblock";

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

// INICIO - Configuración de la tabla Versiones
var tabla_airblocks = $(ID_TABLA_AIRBLOCKS).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ipl><"clear">',
	searching:  true,
	data: JSON.parse(airblocksJson),
	columnDefs: [{ 
		targets: 0,
		visible: false
    }],
	columns: [
	  {data: "id"},
	  {data: "nombre", name: "nombre", title: "Nombre"}, 
	  {data: "flMin", name: "flMin", title: "FL Min"}, 
	  {data: "flMax", name: "flMax", title: "FL Max"},
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
	 rowElement = tabla_airblocks.row(this).data();
	 idElement = tabla_airblocks.row(this).data()[0]
});

// Campo para el filtro
$('#tablaAirblocks tfoot th').each(function() {
    var foot = $('#tablaAirblocks tfoot th').eq($(this).index()).text();
    $(this).html('<input type="text" class="form-control" placeholder="Filtrar por ' + foot + '" />');
});
  
// Funcionalidad del filtro
$("#tablaAirblocks tfoot input").on('keyup change', function() {
	tabla_airblocks.column($(this).parent().index() + ':visible')
    	.search(this.value)
        .draw();
});

$(ID_INPUT_SEARCH_AIRBLOCKS).on('keyup change', function() {
    tabla_airblocks.columns()
    	.data()
    	.search(this.value)
        .draw();
});

// FIN - Configuración de la tabla Versiones
// Configuración de la tabla del popup para seleccionar Versiones
var tabla_seleccionar_airblocks =  $(ID_TABLA_SELECCIONAR_AIRBLOCK).DataTable({
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
    $(ID_BOTON_ACEPTAR_SELECCIONAR_AIRBLOCK).removeAttr('disabled');
 })
 .on('deselect', function() {
    $(ID_BOTON_ACEPTAR_SELECCIONAR_AIRBLOCK).attr('disabled', 'disabled');
 })
 .on('click', 'tr', function() {
	 rowNode = this;
	 rowElement = tabla_seleccionar_airblocks.row(this).data();
	 idElement = tabla_seleccionar_airblocks.row(this).data()[0];
});

// Botón Borrar
$(ID_BOTON_BORRAR_AIRBLOCK).on('click', function () {
	deleteAirblock($(ID_SECTOR_ATC).val(), rowElement.id);
});

// Botón Aceptar del popup
$(ID_BOTON_ACEPTAR_SELECCIONAR_AIRBLOCK).on('click', function () {
	insertAirblock($(ID_SECTOR_ATC).val(), rowElement[0]);
});

function addAirblockToSectorATCTable(){
	
	// 1. Eliminamos la fila de la tabla de versiones del popup
	$(ID_TABLA_SELECCIONAR_AIRBLOCK).DataTable()
		.row(rowNode)
		.remove()
		.draw();
	
	// 2. Insertamos la fila eliminada, en la tabla de versiones
	$(ID_TABLA_AIRBLOCKS).DataTable()
		.row
		.add({id: rowElement[0], 
			nombre: rowElement[1],
		    flMin: 	rowElement[2], 
		    flMax: rowElement[3],
			descripcion: rowElement[4]
		}).draw();
	
	// 3. Deseleccionamos todas las filas de la tabla de versiones
	$(ID_TABLA_AIRBLOCKS).DataTable().rows().deselect();
	// 4. Deshabilitamos el botón aceptar
	$(ID_TABLA_SELECCIONAR_AIRBLOCK).attr('disabled', 'disabled');
}

function insertAirblock(idSectorATC, idAirblock){

    $.ajax({
        type: 'POST',
        url: urlInsertAirblock + idSectorATC + "/" + idAirblock,
        data: JSON.stringify({}),
        dataType: 'json',
        contentType: 'application/json',
        cache: false,
        processData:false,
        success: function(response) { 
			addAirblockToSectorATCTable();   
        },
        error: function(e) {
			alert(e); 
        }
    });
}

function deleteAirblockToSectorATCTable(){
	
	if (tabla_airblocks.rows('.selected').any()){
		// 1. Eliminamos la fila de la tabla de versiones
		$(ID_TABLA_AIRBLOCKS).DataTable()
		.row(rowNode)
		.remove()
		.draw();
	
		// 2. Insertamos la fila eliminada, en la tabla de versiones del popup
		$(ID_TABLA_SELECCIONAR_AIRBLOCK).DataTable()
			.row
			.add([rowElement.id, 
			  	  rowElement.nombre, 
			  	  rowElement.flMin,
			  	  rowElement.flMax,
			  	  rowElement.descripcion
			])
			.draw();
		
		// 3. Deseleccionamos todas las filas de la tabla de versiones del popup
		$(ID_TABLA_SELECCIONAR_AIRBLOCK).DataTable().rows().deselect();
	}
}

function deleteAirblock(idSectorATC, idAirblock){

    $.ajax({
        type: 'DELETE',
        url: urlDeleteAirblock + idSectorATC + "/" + idAirblock,
        data: JSON.stringify({}),
        dataType: 'json',
        contentType: 'application/json',
        cache: false,
        processData:false,
        success: function(response) { 
			deleteAirblockToSectorATCTable();     
        },
        error: function(e) {
			alert(e); 
        }
    });
}

$(ID_MODAL_AIRBLOCK).on('show.bs.modal', function () {
	$(ID_BOTON_ACEPTAR_SELECCIONAR_AIRBLOCK).attr('disabled', 'disabled');
})