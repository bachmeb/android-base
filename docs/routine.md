
####Make DTO
	Define fields
	Make getters/setters
####Make DAO interface
	Define CRUDL methods
####Make DAO
	extend SQLiteOpenHelper
	implement DAO interface
	implement abstract methods
	create constructor
	send Context object to super constructor in constructor
	define static fields for table name, primary key, and all fields in DTO
	create populateObjectFromCursor() method
	Create onUpdate() method

####Make Service interface
	Define methods to match UI buttons
####Make Service class
	Implement Service interface
	Instantiate DAO object
	Call DAO methods in Serivce methods to read/write to DB
