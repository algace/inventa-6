const ID_DATETIMEPICKER_FECHA = '#datetimepickerFecha';

var rowNode;

// INICIO - Configuración para los campos de fecha y hora
$(ID_DATETIMEPICKER_FECHA).datetimepicker({
	widgetPositioning: {
	    horizontal: 'right',
	    vertical: 'bottom'
	},
	locale: 'es',
	format: 'YYYY-MM-DD'
});

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

// INICIO - Al cambiar el tipo de unidad debe cambiar el valor de la unidad y su nombre
var unidadPrevia = document.getElementById("tipoUnidadFrecuencia.id").selectedOptions[0].text;

function gestionarCambioUnidad(unidad) {

    var unidadActual = unidad.selectedOptions[0].text;
    var valor = document.getElementById("valor").value;
    
    if (unidadActual == "") {
		document.getElementById("nombre").defaultValue = '';
	} else {

    	//codigo para calcular el nuevo valor
    	if (valor != "") {
			switch (unidadPrevia) {
				case 'KHz':
					if (unidadActual == "MHz") {
						valor = +(valor * 0.001).toFixed(3);				
					}
					if (unidadActual == "GHz") {
						valor = +(valor * 0.000001).toFixed(6);
					}
					break;
				case 'MHz':
					if (unidadActual == "KHz") {
						valor = +(valor * 1000);				
					}
					if (unidadActual == "GHz") {
						valor = +(valor * 0.001).toFixed(6);
					}
					break;
				case 'GHz':
					if (unidadActual == "KHz") {
						valor = +(valor * 1000000);				
					}
					if (unidadActual == "MHz") {
						valor = +(valor * 1000);
					}
					break;
			}
		
			//Actualizacion del valor
			if (valor % 1 == 0) {
				valor = Math.round(valor);
			}
			$("#valor").val(valor);
		
			//Actualización del nombre
			document.getElementById("nombre").defaultValue = valor + ' ' + unidadActual;
		}
    }
    unidadPrevia = unidadActual;
};
// FIN

// INICIO - Al cambiar el valor debe cambiar el nombre de la unidad
function cambioValor(campoValor) {
	
	var valor = campoValor.value;
	var unidadActual = document.getElementById("tipoUnidadFrecuencia.id").selectedOptions[0].text;
	
	if (unidadActual != "") {
		if (valor != "") {
			document.getElementById("nombre").defaultValue = valor + ' ' + unidadPrevia;
		} else {
			document.getElementById("nombre").defaultValue = '';
		}
	}
};
// FIN