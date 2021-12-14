/*!
 * Funciones propias de version.html
 */

const ID_BOTON_BORRAR_APLICACION = '#botonBorrarAplicacion';
const ID_BOTON_ACEPTAR_SELECCIONAR_APLICACION = '#botonAceptarSeleccionarAplicacion';
const ID_TABLA_APLICACIONES = '#tablaAplicaciones';
const ID_TABLA_SELECCIONAR_APLICACION = '#tablaSeleccionarAplicaciones';
const ID_MODAL_APLICACION = "#popupSeleccionarAplicacionesSW";

var rowElement = null;
var idElement  = null;
var rowNode = null;

// INICIO - Configuración de la tabla Aplicacion
var tabla_aplicaciones = $(ID_TABLA_APLICACIONES).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ifpl><"clear">',
	searching:  false,
	data: JSON.parse(aplicacionesSWJson),
	columnDefs: [{ 
		targets: 0,
		visible: false
    },{ 
		targets: 1,
        render: function(data, type, full, meta){
		   if (type == "display"){
			   var valueId = idElement != null ? idElement : full.id;
	           return data + '<input type="hidden" id="aplicacionesSW' + valueId +'.id" name="aplicacionesSW['+ valueId + '].id" value="' + valueId + '">';
           }else{
			   return data;
		   }
        }
    }],
	columns: [
			  {data: "id"},
			  {data: "nombre", name: "nombre", title: "Nombre"}, 
			  {data: "archivo", name: "archivo", title: "Archivo"}, 
			  {data: "fecha", name: "fecha", title: "Fecha"},
			  {data: "hora", name: "hora", title: "Hora"}],
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
	 rowElement = tabla_aplicaciones.row(this).data();
	 idElement = tabla_aplicaciones.row(this).data()[0];
	 
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

// Configuración de la tabla del popup para seleccionar Versiones
var tabla_seleccionar_aplicaciones = $(ID_TABLA_SELECCIONAR_APLICACION).DataTable({
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
	$(ID_BOTON_ACEPTAR_SELECCIONAR_APLICACION).removeAttr('disabled');
 })
 .on('deselect', function() {
	$(ID_BOTON_ACEPTAR_SELECCIONAR_APLICACION).attr('disabled', 'disabled');
 }) 
 .on('click', 'tr', function() {
	 rowNode = this;
	 rowElement = tabla_seleccionar_aplicaciones.row(this).data();
	 idElement = tabla_seleccionar_aplicaciones.row(this).data()[0];
});

// Botón Borrar
$(ID_BOTON_BORRAR_APLICACION).on('click', function () {
	
	if(tabla_aplicaciones.rows('.selected').any()){
		// 1. Eliminamos la fila de la tabla de versiones
		$(ID_TABLA_APLICACIONES).DataTable()
			.row(rowNode)
			.remove()
			.draw();
		
		// 2. Insertamos la fila eliminada, en la tabla de versiones del popup
		$(ID_TABLA_SELECCIONAR_APLICACION).DataTable()
			.row
			.add([rowElement.id, 
			  	  rowElement.nombre, 
			  	  rowElement.archivo,
			  	  rowElement.fecha,
			  	  rowElement.hora
			])
			.draw();
			
		
		// 3. Deseleccionamos todas las filas de la tabla de versiones del popup
		$(ID_TABLA_SELECCIONAR_APLICACION).DataTable().rows().deselect();
		
		// 4. Deshabilitamos el botón borrar
		$(ID_BOTON_BORRAR_APLICACION).attr('disabled', 'disabled');
	}
});

// Botón Aceptar del popup
$(ID_BOTON_ACEPTAR_SELECCIONAR_APLICACION).on('click', function () {

	// 1. Eliminamos la fila de la tabla de versiones del popup
	$(ID_TABLA_SELECCIONAR_APLICACION).DataTable()
		.row(rowNode)
		.remove()
		.draw();
	
	// 2. Insertamos la fila eliminada, en la tabla de versiones
	$(ID_TABLA_APLICACIONES).DataTable()
		.row
		.add({id: rowElement[0], 
			nombre: rowElement[1], 
			archivo: rowElement[2],
			fecha: rowElement[3],
			hora: rowElement[4]})
		.draw();
	
	// 3. Deseleccionamos todas las filas de la tabla de versiones
	$(ID_TABLA_APLICACIONES).DataTable().rows().deselect();
	
	// 4. Deshabilitamos el botón aceptar
	$(ID_TABLA_SELECCIONAR_APLICACION).attr('disabled', 'disabled');
});

$(ID_MODAL_APLICACION).on('show.bs.modal', function () {
	$(ID_BOTON_ACEPTAR_SELECCIONAR_APLICACION).attr('disabled', 'disabled');
});
