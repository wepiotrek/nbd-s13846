printjson(
    db.people.mapReduce(
		function() {
			this.credit.forEach(c => emit(c.currency, parseFloat(c.balance)));
		},
		function(keys, vals) {
			return vals.reduce((a, b) => a + b);
		}, {
			out: { inline: 1 }
		}));