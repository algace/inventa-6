/*!
 * Funciones propias de equipamiento.html
 */

const ID_BOTON_ACEPTAR_POPUP_SUBIR_DOCUMENTO = '#botonAceptarPopupSubirDocumento';
const ID_BOTON_ASPA_POPUP_SUBIR_DOCUMENTO = '#botonAspaPopupSubirDocumento';
const ID_BOTON_CANCELAR_POPUP_SUBIR_DOCUMENTO = '#botonCancelarPopupSubirDocumento';
const ID_BOTON_BORRAR_DOCUMENTO = '#botonBorrarDocumento'; /********************************** */
const ID_BARRA_PROGRESO_DOC = '#barraProgresoDocumento';
const ID_DESCRIPCION_DOCUMENTO = '#descripcionDocumento';
const ID_ERROR_DESCRIPCION_DOCUMENTO = '#errorDescripcionDocumento';
const ID_ERROR_FICHERO_NO_SELECCIONADO = '#errorFicheroNoSeleccionado';
const ID_ERROR_FICHERO_TAMAGNO_MAX = '#errorFicheroTamagnoMax';
const ID_ERROR_FICHERO_VACIO = '#errorFicheroVacio';
const ID_ERROR_TIPO_DOCUMENTO = '#errorTipoDocumento';
const ID_FICHERO_DOC = '#fichero';
const ID_FORM_SUBIR_DOCUMENTO = '#formSubirDocumento';
const ID_TIPO_DOCUMENTO = '#tipoDocumento';
const ID_TABLA_DOCUMENTOS = '#tablaDocumentos';
const ID_MODAL_DOCUMENTO = '#popupSubirDocumento';
const ID_INPUT_SEARCH_DOCUMENTOS = '#searchDocumentos';

// INICIO - Máscara para campos numéricos
// Permite números positivos y negativos entre 1 y 7 dígitos enteros y 2 dígitos decimales
$("#ganancia, #perdida, #apertura, #diametro").mask('S#.S#S#S#.S#S#S0,00', {
	translation: {
		'S': { 
			pattern: /[-]/, 
			optional: true
		}
	},
	reverse: true, 
	selectOnFocus: true, 
	clearIfNotMatch: true
});
// FIN - Máscara para campos numéricos

// INICIO - Configuración de la tabla documentos
var tabla_documentos = $(ID_TABLA_DOCUMENTOS).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ipl><"clear">',
	searching:  true,
	data: JSON.parse(documentosJson),
	columnDefs: [{ 
		targets: 0,
		visible: false
    },{ 
		targets: 1,
		render: function(data, type, full){
	       			return  '<a class="button-crud" onclick="downloadDocumento(' + full.id + ')"><span class="text-crud">' + data + '</span></a>';
        		}
    }],
	columns: [{data: "id", name: "id", title: "ID"},
			  {data: "nombre", name: "nombre", title: "Nombre"}, 
			  {data: "tipoDocumento.nombre", name: "tipoDocumento", title: "Tipo Documento"}, 
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
     $(ID_BOTON_BORRAR_DOCUMENTO).removeAttr('disabled');
 })
 .on('deselect', function() {
     $(ID_BOTON_BORRAR_DOCUMENTO).attr('disabled', 'disabled');
 })
 .on('click', 'tr', function() {
	 rowNode = this;
	 rowElement = tabla_documentos.row(this).data();
	 idElement = tabla_documentos.row(this).data()[0];
});

// Campo para el filtro
$('#tablaDocumentos tfoot th').each(function() {
    var foot = $('#tablaDocumentos tfoot th').eq($(this).index()).text();
    $(this).html('<input type="text" class="form-control" placeholder="Filtrar por ' + foot + '" />');
});
  
// Funcionalidad del filtro
$("#tablaDocumentos tfoot input").on('keyup change', function() {
	tabla_documentos.column($(this).parent().index() + ':visible')
    	.search(this.value)
        .draw();
});
// FIN - Configuración de la tabla documentos

// Botón Borrar Documentos
$(ID_BOTON_BORRAR_DOCUMENTO).on('click', function () {
	
	deleteDocumento(rowElement.id);
});

//INICIO - Validaciones subir fichero
function validarTamagnoFichero(tamagnoMaximo) {	
	
	if ($(ID_FICHERO_DOC)[0].files[0].size > tamagnoMaximo) {
		$(ID_ERROR_FICHERO_VACIO).attr("hidden", "hidden");
		$(ID_ERROR_FICHERO_TAMAGNO_MAX).removeAttr("hidden");
		$(ID_ERROR_FICHERO_NO_SELECCIONADO).attr("hidden", "hidden");	
		$(ID_BOTON_ACEPTAR_POPUP_SUBIR_DOCUMENTO).attr("disabled", "disabled");
	
	} else if ($(ID_FICHERO_DOC)[0].files[0].size == 0) {
		$(ID_ERROR_FICHERO_TAMAGNO_MAX).attr("hidden", "hidden");
		$(ID_ERROR_FICHERO_VACIO).removeAttr("hidden");
		$(ID_ERROR_FICHERO_NO_SELECCIONADO).attr("hidden", "hidden");
		$(ID_BOTON_ACEPTAR_POPUP_SUBIR_DOCUMENTO).attr("disabled", "disabled");
	
	} else {
		$(ID_ERROR_FICHERO_TAMAGNO_MAX).attr("hidden", "hidden");
		$(ID_ERROR_FICHERO_VACIO).attr("hidden", "hidden");
		$(ID_ERROR_FICHERO_NO_SELECCIONADO).attr("hidden", "hidden");
		$(ID_BOTON_ACEPTAR_POPUP_SUBIR_DOCUMENTO).removeAttr("disabled");
	}
};

function validarFicheroNoSeleccionado() {
	var resultado;

	if (null == $(ID_FICHERO_DOC)[0].files[0]) {
		$(ID_ERROR_FICHERO_NO_SELECCIONADO).removeAttr("hidden");
		resultado = false;
	} else {
		$(ID_ERROR_FICHERO_NO_SELECCIONADO).attr("hidden", "hidden");
		resultado = true;
	}
	return resultado;
}

function validarTipoDocumento() {
	var resultado;
	
	if ("" == $(ID_TIPO_DOCUMENTO).val()) {
		$(ID_ERROR_TIPO_DOCUMENTO).removeAttr("hidden");
		resultado = false;
	} else {
		$(ID_ERROR_TIPO_DOCUMENTO).attr("hidden", "hidden");
		resultado = true;
	}
	return resultado;
}

function validarDescripcion() {
	var resultado;
	
	if ("" == $(ID_DESCRIPCION_DOCUMENTO).val()) {
		$(ID_ERROR_DESCRIPCION_DOCUMENTO).removeAttr("hidden");
		resultado = false;
	} else {
		$(ID_ERROR_DESCRIPCION_DOCUMENTO).attr("hidden", "hidden");
		resultado = true;
	}
	return resultado;
}


function validarPopupDocumento() {
	var contador = 3;
	
	// Limpiamos mensajes de error 
	$(ID_ERROR_FICHERO_VACIO).attr("hidden", "hidden");
	$(ID_ERROR_FICHERO_TAMAGNO_MAX).attr("hidden", "hidden");
	$(ID_ERROR_FICHERO_NO_SELECCIONADO).attr("hidden", "hidden");
	$(ID_ERROR_TIPO_DOCUMENTO).attr("hidden", "hidden");
	$(ID_ERROR_DESCRIPCION_DOCUMENTO).attr("hidden", "hidden");
		
	if (validarFicheroNoSeleccionado()){
		--contador;
	}
	if (validarTipoDocumento()){
		--contador;
	}
	if (validarDescripcion()){
		--contador;
	}
	
	// Mostramos la barra de progreso
	if (contador == 0) {
		$(ID_BARRA_PROGRESO_DOC).removeAttr("hidden");
		insertDocumento($('#id').val());
	} else {
		$(ID_BARRA_PROGRESO_DOC).attr("hidden", "hidden");
	}
		
	return ((contador == 0) ? true : false);
}

function addDocumentoToEquipamientoTable(idDocumento){
	
	$(ID_TABLA_DOCUMENTOS).DataTable()
	.row
	.add({
		id: idDocumento,
		contenido: fileByteArray, 
		nombre: $(ID_FICHERO_DOC)[0].files[0].name, 
		descripcion: $('#descripcionDocumento').val(),
		tipoDocumento: {
			id: $('#tipoDocumento option:selected').val(),
			nombre: $('#tipoDocumento option:selected').text()
		}
	}).draw();
}

function insertDocumento(idEquipamiento){

    $.ajax({
        type: 'POST',
        url: urlInsertDocumento + idEquipamiento,
        data: JSON.stringify({
			contenido: fileByteArray, 
			nombre: $(ID_FICHERO_DOC)[0].files[0].name, 
			descripcion: $('#descripcionDocumento').val(),
			tipoDocumento: {
				id: $('#tipoDocumento option:selected').val(),
				nombre: $('#tipoDocumento option:selected').text()
			}
		}),
        dataType: 'json',
        contentType: 'application/json',
        cache: false,
        processData:false,
        success: function(response) { 
			addDocumentoToEquipamientoTable(response);   
        },
        error: function(e) {
			alert(e); 
        }
    });
}

function deleteDocumentoToEquipamientoTable(){
	
	if (tabla_documentos.rows('.selected').any()){
		// 1. Eliminamos la fila de la tabla
		$(ID_TABLA_DOCUMENTOS).DataTable()
			.row(rowNode)
			.remove()
			.draw();
			
		// 2. Deshabilitamos el botón borrar
		$(ID_BOTON_BORRAR_DOCUMENTO).attr('disabled', 'disabled');
	}
}

function deleteDocumento(idDocumento){

    $.ajax({
        type: 'DELETE',
        url: urlDeleteDocumento + idDocumento,
        data: JSON.stringify({}),
        dataType: 'json',
        contentType: 'application/json',
        cache: false,
        processData:false,
        success: function(response) { 
			deleteDocumentoToEquipamientoTable();     
        },
        error: function(e) {
			alert(e); 
        }
    });
}

function downloadDocumento(idDocumento){
	
	$.ajax({
        type: 'GET',
        url: urlDownloadDocumento + idDocumento,
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
		        link.setAttribute('href', 'data:application/octet-stream;base64,' + documento.contenido);
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

// Reset de los campos del formulario del popup de subir documento
function resetFormPopupSubirDocumento() {
	$('.custom-file-label').html('');
	$(ID_FICHERO_DOC).value = "";
	$(ID_FORM_SUBIR_DOCUMENTO)[0].reset();
}

// Icono aspa del popup de subir documento
$(ID_BOTON_ASPA_POPUP_SUBIR_DOCUMENTO).on('click', function() {
	resetFormPopupSubirDocumento();
});

// Botón Cancelar del popup de subir documento
$(ID_BOTON_CANCELAR_POPUP_SUBIR_DOCUMENTO).on('click', function() {
	resetFormPopupSubirDocumento();
});

$(ID_MODAL_DOCUMENTO).on('show.bs.modal', function () {
	resetFormPopupSubirDocumento();
});

$(ID_INPUT_SEARCH_DOCUMENTOS).on('keyup change', function() {
    tabla_documentos.columns()
    	.data()
    	.search(this.value)
        .draw();
});
