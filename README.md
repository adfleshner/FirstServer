# FirstServer

Creating my first java webservice.

Currently has a Get Method That gets a list of Person
Each Person has a id name, job, age,imageUrl, and a list of Pets
Each Pet has id, name, age, and imageUrl.

Methods 

```java 
  @RequestMapping(value = "/people", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Person>> getPeople (@RequestParam(required = false) String name, @RequestParam(required = false) String hasAnimals)
    
    @RequestMapping(value = "/person/add", method = RequestMethod.POST)
    public ResponseEntity<String> addPerson(@RequestBody String json) 
    
    @RequestMapping(value = "/person/edit", method = RequestMethod.PUT)
    public ResponseEntity<String> editPerson(@RequestBody String json) 
    
    @RequestMapping(value = "/person/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePerson(@RequestParam(value = "id") long id) 
```
### API
Restful Webservice
www.#yourWebSiteHere#.com/people
