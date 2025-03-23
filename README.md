## SpringBoot_Project


### Notes
When working with JPA and repositories.
And when you want to save the data. 
Then the automatic fields need to be `null` or not set.
But if you search anything by `.find..()` 
then you need to give the `id` or the correct 
information about it in order for it to be searched.

Place `@Transactional` to every class or method that uses the JPA
repository to communicate with the database, because every query that modifies
uses transactions and so you need `@Transactional` even in test classes.
That is way we have to learn from a documentation, but if there is no good 
documentation then we rely on the developers on the internet. With enough research we
learn how to do it, and then we use Google.com less.