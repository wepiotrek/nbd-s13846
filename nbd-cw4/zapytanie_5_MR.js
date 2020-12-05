var map = function(){
	if (this.nationality == "Poland" && this.sex == "Female")
		for(i = 0; i < this.credit.length; i++) {
		emit(this.credit[i].currency, parseFloat(this.credit[i].balance))
	    	}
}
var reduce = function(key, values){
	SumaSrodkow = Array.sum(values),
	SredniaSrodkow = SumaSrodkow/values.length
	
	return key, {SredniaSrodkow, SumaSrodkow}
}
db.people.mapReduce(map,reduce,{out: "NBD5MR"});
printjson(db.NBD5MR.find().toArray());