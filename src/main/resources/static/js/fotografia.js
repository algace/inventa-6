const ID_BOTON_ACEPTAR_POPUP_SUBIR_FOTOGRAFIA = '#botonAceptarPopupSubirFotografia';
const ID_BOTON_ASPA_POPUP_SUBIR_FOTOGRAFIA = '#botonAspaPopupSubirFotografia';
const ID_BOTON_CANCELAR_POPUP_SUBIR_FOTOGRAFIA = '#botonCancelarPopupSubirFotografia';
const ID_BOTON_BORRAR_FOTOGRAFIA = '#botonBorrarFotografia';/***************************/
const ID_BARRA_PROGRESO_FOTOGRAFIA = '#barraProgresoFotografia';
const ID_DESCRIPCION_FOTOGRAFIA = '#descripcionFotografia';
const ID_ERROR_DESCRIPCION_FOTOGRAFIA = '#errorDescripcionFotografia';
const ID_ERROR_FOTOGRAFIA_NO_SELECCIONADO = '#errorFotografiaNoSeleccionado';
const ID_ERROR_FOTOGRAFIA_TAMAGNO_MAX = '#errorFotografiaTamagnoMax';
const ID_ERROR_FOTOGRAFIA_VACIO = '#errorFotografiaVacio';
const ID_FOTOGRAFIA_FOTO = '#fotografia';
const ID_FORM_SUBIR_FOTOGRAFIA = '#formSubirFotografia';
const ID_TABLA_FOTOGRAFIAS = '#tablaFotografias';
const ID_MODAL_FOTOGRAFIA = '#popupSubirFotografia';
const ID_INPUT_SEARCH_FOTOGRAFIAS = '#searchFotografias';

var rowElement = null;
var idElement  = null;
var rowNode = null;

// INICIO - Configuración de la tabla fotografias
var tabla_fotografias = $(ID_TABLA_FOTOGRAFIAS).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ipl><"clear">',
	searching:  true,
	data: JSON.parse(fotografiasJson),
	columnDefs: [{ 
		targets: 0,
		visible: false
    },{ 
		targets: 1,
		render: function(data, type, full){
	       			return  '<a class="button-crud" onclick="downloadFotografia(' + full.id + ')"><span class="text-crud">' + data + '</span></a>';
        		}
    }],
	columns: [{data: "id", name: "id", title: "ID"},
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
     $(ID_BOTON_BORRAR_FOTOGRAFIA).removeAttr('disabled');
 })
 .on('deselect', function() {
     $(ID_BOTON_BORRAR_FOTOGRAFIA).attr('disabled', 'disabled');
 })
 .on('click', 'tr', function() {
	 rowNode = this;
	 rowElement = tabla_fotografias.row(this).data();
	 idElement = tabla_fotografias.row(this).data()[0];
});

// Campo para el filtro
$('#tablaFotografias tfoot th').each(function() {
    var foot = $('#tablaFotografias tfoot th').eq($(this).index()).text();
    $(this).html('<input type="text" class="form-control" placeholder="Filtrar por ' + foot + '" />');
});
  
// Funcionalidad del filtro
$("#tablaFotografias tfoot input").on('keyup change', function() {
	tabla_fotografias.column($(this).parent().index() + ':visible')
    	.search(this.value)
        .draw();
});
// FIN - Configuración de la tabla fotografias

// Botón Borrar Fotografias
$(ID_BOTON_BORRAR_FOTOGRAFIA).on('click', function () {
	deleteFotografia(rowElement.id);
});

//INICIO - Validaciones subir fotografia
function validarTamagnoFotografia(tamagnoMaximo) {	
	
	if ($(ID_FOTOGRAFIA_FOTO)[0].files[0].size > tamagnoMaximo) {
		$(ID_ERROR_FOTOGRAFIA_VACIO).attr("hidden", "hidden");
		$(ID_ERROR_FOTOGRAFIA_TAMAGNO_MAX).removeAttr("hidden");
		$(ID_ERROR_FOTOGRAFIA_NO_SELECCIONADO).attr("hidden", "hidden");	
		$(ID_BOTON_ACEPTAR_POPUP_SUBIR_FOTOGRAFIA).attr("disabled", "disabled");
	
	} else if ($(ID_FOTOGRAFIA_FOTO)[0].files[0].size == 0) {
		$(ID_ERROR_FOTOGRAFIA_TAMAGNO_MAX).attr("hidden", "hidden");
		$(ID_ERROR_FOTOGRAFIA_VACIO).removeAttr("hidden");
		$(ID_ERROR_FOTOGRAFIA_NO_SELECCIONADO).attr("hidden", "hidden");
		$(ID_BOTON_ACEPTAR_POPUP_SUBIR_FOTOGRAFIA).attr("disabled", "disabled");
	
	} else {
		$(ID_ERROR_FOTOGRAFIA_TAMAGNO_MAX).attr("hidden", "hidden");
		$(ID_ERROR_FOTOGRAFIA_VACIO).attr("hidden", "hidden");
		$(ID_ERROR_FOTOGRAFIA_NO_SELECCIONADO).attr("hidden", "hidden");
		$(ID_BOTON_ACEPTAR_POPUP_SUBIR_FOTOGRAFIA).removeAttr("disabled");
	}
};

function validarFotografiaNoSeleccionado() {
	var resultado;

	if (null == $(ID_FOTOGRAFIA_FOTO)[0].files[0]) {
		$(ID_ERROR_FOTOGRAFIA_NO_SELECCIONADO).removeAttr("hidden");
		resultado = false;
	} else {
		$(ID_ERROR_FOTOGRAFIA_NO_SELECCIONADO).attr("hidden", "hidden");
		resultado = true;
	}
	return resultado;
}

function validarDescripcionFotografia() {
	var resultado;
	
	if ("" == $(ID_DESCRIPCION_FOTOGRAFIA).val()) {
		$(ID_ERROR_DESCRIPCION_FOTOGRAFIA).removeAttr("hidden");
		resultado = false;
	} else {
		$(ID_ERROR_DESCRIPCION_FOTOGRAFIA).attr("hidden", "hidden");
		resultado = true;
	}
	return resultado;
}


function validarPopupFotografia() {
	var contador = 2;
	
	// Limpiamos mensajes de error 
	$(ID_ERROR_FOTOGRAFIA_VACIO).attr("hidden", "hidden");
	$(ID_ERROR_FOTOGRAFIA_TAMAGNO_MAX).attr("hidden", "hidden");
	$(ID_ERROR_FOTOGRAFIA_NO_SELECCIONADO).attr("hidden", "hidden");
	$(ID_ERROR_DESCRIPCION_FOTOGRAFIA).attr("hidden", "hidden");
		
	if (validarFotografiaNoSeleccionado()){
		--contador;
	}
	if (validarDescripcionFotografia()){
		--contador;
	}
	
	// Mostramos la barra de progreso
	if (contador == 0) {
		$(ID_BARRA_PROGRESO_FOTOGRAFIA).removeAttr("hidden");
		insertFotografia($('#id').val());
	} else {
		$(ID_BARRA_PROGRESO_FOTOGRAFIA).attr("hidden", "hidden");
	}
		
	return ((contador == 0) ? true : false);
}

function addFotografiaToEquipamientoTable(idFotografia){
	
	$(ID_TABLA_FOTOGRAFIAS).DataTable()
	.row
	.add({
		id: idFotografia,
		contenido: fileByteArray, 
		nombre: $(ID_FOTOGRAFIA_FOTO)[0].files[0].name, 
		descripcion: $('#descripcionFotografia').val(),
	}).draw();
}

function insertFotografia(idEquipamiento){

    $.ajax({
        type: 'POST',
        url: urlInsertFotografia + idEquipamiento,
        data: JSON.stringify({
			contenido: fileByteArray, 
			nombre: $(ID_FOTOGRAFIA_FOTO)[0].files[0].name, 
			descripcion: $('#descripcionFotografia').val(),
		}),
        dataType: 'json',
        contentType: 'application/json',
        cache: false,
        processData:false,
        success: function(response) { 
			addFotografiaToEquipamientoTable(response);   
        },
        error: function(e) {
			alert(e); 
        }
    });
}

function deleteFotografiaToEquipamientoTable(){
	
	if (tabla_fotografias.rows('.selected').any()){
		// 1. Eliminamos la fila de la tabla
		$(ID_TABLA_FOTOGRAFIAS).DataTable()
			.row(rowNode)
			.remove()
			.draw();
			
		// 2. Deshabilitamos el botón borrar
		$(ID_BOTON_BORRAR_FOTOGRAFIA).attr('disabled', 'disabled');
	}
}

function deleteFotografia(idFotografia){

    $.ajax({
        type: 'DELETE',
        url: urlDeleteFotografia + idFotografia,
        data: JSON.stringify({}),
        dataType: 'json',
        contentType: 'application/json',
        cache: false,
        processData:false,
        success: function(response) { 
			deleteFotografiaToEquipamientoTable();     
        },
        error: function(e) {
			alert(e); 
        }
    });
}

function downloadFotografia(idFotografia){
	
	$.ajax({
        type: 'GET',
        url: urlDownloadFotografia + idFotografia,
        dataType: 'json',
        contentType: 'application/json',
        cache: false,
        processData:false,
		success: function(documento) { 
		  if (navigator.msSaveBlob) {
		     // IE 10+
		     navigator.msSaveBlob(blob, documento.nombre);
		  }else {
		     var link = document.createElement('a');
		     // Browsers that support HTML5 download attribute
		     if (link.download !== undefined) {
		        link.setAttribute('href', 'data:application/octet-stream;base64,' + documento.contenido)
		        link.setAttribute('download', documento.nombre);
		        link.style.visibility = 'hidden';
		        document.body.appendChild(link);
		        link.click();
		        document.body.removeChild(link);
		     }
		  }
        },  
        error: function(e) {
			alert(e); 
        }
    });
}

// Reset de los campos del formulario del popup de subir fotografia
function resetFormPopupSubirFotografia() {
	$('.custom-file-label').html('');
	$(ID_FOTOGRAFIA_FOTO).value = "";
	$(ID_FORM_SUBIR_FOTOGRAFIA)[0].reset();
}

// Icono aspa del popup de subir fotografia
$(ID_BOTON_ASPA_POPUP_SUBIR_FOTOGRAFIA).on('click', function() {
	resetFormPopupSubirFotografia();
});

// Botón Cancelar del popup de subir fotografia
$(ID_BOTON_CANCELAR_POPUP_SUBIR_FOTOGRAFIA).on('click', function() {
	resetFormPopupSubirFotografia();
});

$(ID_MODAL_FOTOGRAFIA).on('show.bs.modal', function () {
	resetFormPopupSubirFotografia();
});

$(ID_INPUT_SEARCH_FOTOGRAFIAS).on('keyup change', function() {
    tabla_fotografias.columns()
    	.data()
    	.search(this.value)
        .draw();
});

var reader = new FileReader();
var fileByteArray = [];

// Seteamos el nombre del fichero en el input de selección del fichero
$('input[type="file"]').on('change', function(e){
	var nombre = e.target.files[0].name;    
	$(this).next('.custom-file-label').html(nombre);
 	fileByteArray = [];
  	reader.readAsArrayBuffer(e.target.files[0]);
  	reader.onloadend = (evt) => {
    if (evt.target.readyState === FileReader.DONE) {
      const arrayBuffer = evt.target.result,
        array = new Uint8Array(arrayBuffer);
      for (const a of array) {
        fileByteArray.push(a);
      }
    }
  }
});
