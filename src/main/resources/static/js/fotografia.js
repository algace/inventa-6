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

var rowNode;


// Reset de los campos del formulario del popup de subir fotografia
function resetFormPopupSubirFotografia() {
	$('.custom-file-label').html('');
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

// INICIO - Configuración de la tabla fotografias
var tabla_fotografias = $(ID_TABLA_FOTOGRAFIAS).DataTable({
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
     $(ID_BOTON_BORRAR_FOTOGRAFIA).removeAttr('disabled');
 })
 .on('deselect', function() {
     $(ID_BOTON_BORRAR_FOTOGRAFIA).attr('disabled', 'disabled');
 })
 .on('click', 'tr', function() {
	 rowNode = this;
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
	
	// 1. Eliminamos la fila de la tabla
	$(ID_TABLA_FOTOGRAFIAS).DataTable()
		.row(rowNode)
		.remove()
		.draw();
		
	// 2. Deshabilitamos el botón borrar
	$(ID_BOTON_BORRAR_FOTOGRAFIA).attr('disabled', 'disabled');
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
		/*equipamiento.fotografias.push({'id':1, 'nombre': $(ID_FOTOGRAFIA_FOTO)[0].files[0], 'descripcion': 'description'});*/
	} else {
		$(ID_BARRA_PROGRESO_FOTOGRAFIA).attr("hidden", "hidden");
	}
		
	return ((contador == 0) ? true : false);
}

// Seteamos el nombre del fotografia en el input de selección del fotografia
$('input[type="file"]').on('change', function(e){
	var nombre = e.target.files[0].name;    
	$(this).next('.custom-file-label').html(nombre);
});
// FIN - Validaciones subir fotografia

// INICIO - Subir archivo
$(ID_FORM_SUBIR_FOTOGRAFIA).on('submit', function(e) {	 
    e.preventDefault();
    $.ajax({
        xhr: function() {
            var xhr = new window.XMLHttpRequest();
            xhr.upload.addEventListener('progress', function(evt) {
                if (evt.lengthComputable) {
                    var percentComplete = ((evt.loaded / evt.total) * 100);
                    $('.progress-bar').width(percentComplete + '%');
                    $('.progress-bar').html(percentComplete + '%');
                }  
            }, false);
            return xhr;
        },
        type: 'POST',
        url: this.action,
        data: new FormData(this),
        contentType: false,
        cache: false,
        processData:false,
        beforeSend: function() {
            $('.progress-bar').width('0%');           
            $(ID_BARRA_PROGRESO_FOTOGRAFIA).removeAttr("hidden");           
        },
        success: function(response) {      
        	// Recargamos la página ya que desde el backoffice no la recarga 
        	// por hacer la subida del fotografia con ajax 
        	location.reload();              
        },
        error: function(e) {
        }
    });
});