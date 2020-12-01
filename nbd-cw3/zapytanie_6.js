db.people.insertOne( 
	{ "sex": "Male",
	  "first_name": "Piotr",
	  "last_name": "Wezgraj",
	  "job": "Student",
	  "email": "s13846@pjwstk.edu.pl",
	  "location": {"city": "Warsaw",
				  "address": {"streetname": "Koszykowa",
							  "streetnumber": "86"}},
	  "description": "No pain, no gain.",
	  "height": "169",
	  "weight": "65",
	  "birth_date": "1995-12-28",
	  "nationality": ": Poland",
	  "credit": [ {"type": "visa-electron",
				  "number": "412345678969",
				  "currency": "PLN",
				  "balance": "3769"} ]}
);

printjson(db.people.find({first_name:  "Piotr"}).toArray());