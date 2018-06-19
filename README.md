# Job-Scheduling

Notes (6/18/2018):
* `dataGenerator` method in `JobScheduler` class should be separated into a different class called `DataGenerator`.
* Inject dependency of `DataGenerator` class in `JobScheduler` class, and should be passed in constructor during creation.
* `DataGenrator` class should also save the job data in a csv file of following format, where each row describes one job in sequence index,processing time,due date,ready time,weight
* `DataGenerator` class should have a method to read a job file (described above) and load job data, as an alternative to generating data by Pott's method.
