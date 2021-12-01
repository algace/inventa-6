/*!
 * Funciones propias de usuario.html
 */

var rowNode;

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

 .on('click', 'tr', function() {
	 rowNode = this;
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
	
	// 1. Eliminamos la fila de la tabla
	$(ID_TABLA_DOCUMENTOS).DataTable()
		.row(rowNode)
		.remove()
		.draw();
		
	// 2. Deshabilitamos el botón borrar
	$(ID_BOTON_BORRAR_DOCUMENTO).attr('disabled', 'disabled');
});

//INICIO - Validaciones subir fichero
function validarTamagnoFichero(tamagnoMaximo) {	
	
	if ($(ID_FICHERO)[0].files[0].size > tamagnoMaximo) {
		$(ID_ERROR_FICHERO_VACIO).attr("hidden", "hidden");
		$(ID_ERROR_FICHERO_TAMAGNO_MAX).removeAttr("hidden");
		$(ID_ERROR_FICHERO_NO_SELECCIONADO).attr("hidden", "hidden");	
		$(ID_BOTON_ACEPTAR_POPUP_SUBIR_DOCUMENTO).attr("disabled", "disabled");
	
	} else if ($(ID_FICHERO)[0].files[0].size == 0) {
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

	if (null == $(ID_FICHERO)[0].files[0]) {
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
		$(ID_BARRA_PROGRESO).removeAttr("hidden");
	} else {
		$(ID_BARRA_PROGRESO).attr("hidden", "hidden");
	}
		
	return ((contador == 0) ? true : false);
}

// Seteamos el nombre del fichero en el input de selección del fichero
$('input[type="file"]').on('change', function(e){
	var nombre = e.target.files[0].name;    
	$(this).next('.custom-file-label').html(nombre);
});
// FIN - Validaciones subir fichero

// INICIO - Subir archivo
$(ID_FORM_SUBIR_DOCUMENTO).on('submit', function(e) {	 
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
            $(ID_BARRA_PROGRESO).removeAttr("hidden");           
        },
        success: function(response) {      
        	// Recargamos la página ya que desde el backoffice no la recarga 
        	// por hacer la subida del fichero con ajax 
        	location.reload();              
        },
        error: function(e) {
        }
    });
});
