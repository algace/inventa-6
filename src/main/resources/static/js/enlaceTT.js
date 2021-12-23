/*!
 * Funciones propias de redTT.html
 */

const ID_TABLA_ENLACES_TT = '#tablaEnlacesTT';
const ID_BOTON_ACEPTAR_SELECCIONAR_ENLACE_TT = '#botonAceptarSeleccionarEnlaceTT';
const ID_BOTON_BORRAR_ENLACE_TT = '#botonBorrarEnlaceTT';
const ID_TABLA_SELECCIONAR_ENLACES_TT = '#tablaSeleccionarEnlacesTT';
const ID_MODAL_ENLACES_TT = "#popupSeleccionarEnlacesTT";
const ID_INPUT_SEARCH_ENLACES_TT = "#searchEnlacesTT";
const ID_INPUT_SEARCH_SELECCIONAR_ENLACES_TT = "#searchSeleccionarEnlacesTT";

var rowElement = null;
var idElement  = null;
var rowNode = null;

// INICIO - Configuración de la tabla de enlaces T/T
var tabla_enlaces_TT = $(ID_TABLA_ENLACES_TT).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ipl><"clear">',
	data: JSON.parse(enlacesTTJson),
	searching:  true,
	columnDefs: [{ 
		targets: 0,
		visible: false
    },{ 
		targets: 1,
        render: function(data, type, full, meta){
		   if (type == "display"){
			   var valueId = idElement != null ? idElement : full.id;
	           return data + '<input type="hidden" id="enlacesTT' + valueId +'.id" name="enlacesTT['+ valueId + '].id" value="' + valueId + '">';
           }else{
			   return data;
		   }
        }
    }],
	columns: [
			  {data: "id"},
			  {data: "enlaceTT", name: "enlaceTT", title: "Enlace T/T"}
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
	 rowElement = tabla_enlaces_TT.row(this).data();
	 idElement = tabla_enlaces_TT.row(this).data()[0];
	 
});

//Campo para el filtro
$('#tablaEnlacesTT tfoot th').each(function() {
    var foot = $('#tablaEnlacesTT tfoot th').eq($(this).index()).text();
    $(this).html('<input type="text" class="form-control" placeholder="Filtrar por ' + foot + '" />');
});
  
// Funcionalidad del filtro
$("#tablaEnlacesTT tfoot input").on('keyup change', function() {
	tabla_enlaces_TT.column($(this).parent().index() + ':visible')
    	.search(this.value)
        .draw();
});
// FIN - Configuración de la tabla de enlaces T/T

// Configuración de la tabla del popup para seleccionar enlaces T/T
var tabla_seleccionar_enlaces_TT = $(ID_TABLA_SELECCIONAR_ENLACES_TT).DataTable({
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
     $(ID_BOTON_ACEPTAR_SELECCIONAR_ENLACE_TT).removeAttr('disabled');
 })
 .on('deselect', function() {
     $(ID_BOTON_ACEPTAR_SELECCIONAR_ENLACE_TT).attr('disabled', 'disabled');
 }) 
 .on('click', 'tr', function() {
	 rowNode = this;
	 rowElement = tabla_seleccionar_enlaces_TT.row(this).data();
	 idElement = tabla_seleccionar_enlaces_TT.row(this).data()[0];
});

// Botón Borrar
$(ID_BOTON_BORRAR_ENLACE_TT).on('click', function () {
	
	if(tabla_enlaces_TT.rows('.selected').any()){
		// 1. Eliminamos la fila de la tabla de enlaces T/T
		$(ID_TABLA_ENLACES_TT).DataTable()
			.row(rowNode)
			.remove()
			.draw();
		
		// 2. Insertamos la fila eliminada, en la tabla de enlaces T/T del popup
		$(ID_TABLA_SELECCIONAR_ENLACES_TT).DataTable()
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
			
			
		// 3. Deseleccionamos todas las filas de la tabla de enlaces T/T del popup
		$(ID_TABLA_SELECCIONAR_ENLACES_TT).DataTable().rows().deselect();
		
		// 4. Deshabilitamos el botón borrar
		$(ID_BOTON_BORRAR_ENLACE_TT).attr('disabled', 'disabled');
	}
});

// Botón Aceptar del popup
$(ID_BOTON_ACEPTAR_SELECCIONAR_ENLACE_TT).on('click', function () {

	// 1. Eliminamos la fila de la tabla de enlaces T/T del popup
	$(ID_TABLA_SELECCIONAR_ENLACES_TT).DataTable()
		.row(rowNode)
		.remove()
		.draw();
		

	// 2. Insertamos la fila eliminada, en la tabla de enlaces T/T
	$(ID_TABLA_ENLACES_TT).DataTable()
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
	
	// 3. Deseleccionamos todas las filas de la tabla de enlaces T/T
	$(ID_TABLA_ENLACES_TT).DataTable().rows().deselect();
	
	// 4. Deshabilitamos el botón aceptar
	$(ID_TABLA_SELECCIONAR_ENLACES_TT).attr('disabled', 'disabled');
});

$(ID_MODAL_ENLACES_TT).on('show.bs.modal', function () {
	$(ID_BOTON_ACEPTAR_SELECCIONAR_ENLACE_TT).attr('disabled', 'disabled');
});

$(ID_INPUT_SEARCH_ENLACES_TT).on('keyup change', function() {
    tabla_enlaces_TT.columns()
    	.data()
    	.search(this.value)
        .draw();
});

$(ID_INPUT_SEARCH_SELECCIONAR_ENLACES_TT).on('keyup change', function() {
    tabla_seleccionar_enlaces_TT.columns()
    	.data()
    	.search(this.value)
        .draw();
});

