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