printjson(
    db.people.mapReduce(
		function() {
			emit(this.sex, { weight: parseFloat(this.weight), height: parseFloat(this.height) }); 
		},
		function(keys, vals) { 
			let w = vals.map(v => v.weight);
			let h = vals.map(v => v.height);
			const avg = arr => arr.reduce((a, b) => a + b) / arr.length;
			return {
				weight: avg(w),
				height: avg(h)
			}; 
		}, {out: { inline: 1 }} )
);