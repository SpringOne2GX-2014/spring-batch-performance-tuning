Spring XD Image Scaling Sample
==============================

In this Spring XD sample, user submits images that our job needs to resize. The path to the file is stored in the database, the image on disk residing in the "unprocessed" directory. The job will read the paths from the database and resize the image, writing it to the "processed" directory.

## Requirements

In order for the sample to run you will need to have installed:

* Spring XD ([Instructions](https://github.com/spring-projects/spring-xd/wiki/Getting-Started))

## Building

Build the sample simply by executing:

	$ mvn clean assembly:assembly

As a result, you will see the following files and directories created under `target/xd-multi-threaded-1.0.0.BUILD-SNAPSHOT-bin/`:

```
└── modules
    └── job
        └── image-scaling
            ├── config
            |   ├── application.properties
            │   └── image-scaling.xml
            └── lib
                ├── imgscalr-lib-4.2.jar
                └── original-xd-multi-threaded-1.0.0.BUILD-SNAPSHOT.jar
```

## Running the Sample

**IMPORTANT**: Please ensure that you have defined the `$XD_HOME` environment variable, pointing to the correct *Spring XD* home directory.

In the `xd-multi-threaded` directory

	$ ./copy-files.sh

Now your Sample is ready to be executed. Start your *Spring XD* admin server (If it was already running, you must restart it):

	xd/bin>$ ./xd-singlenode

Now start the *Spring XD Shell* in a separate window:

	shell/bin>$ ./xd-shell

## Setup the process

In this example, the job is driven by a stream rather than being launched using a separate command. A job instance is launched by posting http data to it.

	xd:> job create --name imagescaler --definition "image-scaling --makeUnique=false" --deploy 
	xd:> stream create --name imagescalertap --definition "tap:job:imagescaler > log" --deploy
	
We also create a separate stream sends notifications from the job to the log. This lets us know when the job completes, along with status information.

## Execute the process

	xd:> job launch imagescaler

By default the job will use the images located under `~/image_submissions/unprocessed/`.
	
## Results

The processing should produce output like the following:

````
11:58:06,819  INFO SimpleAsyncTaskExecutor-52 sink.imagescalertap - XdChunkContextInfo [complete=true, stepExecution=StepExecution: id=11, version=53, name=processImages, status=STARTED, exitStatus=EXECUTING, readCount=4911, filterCount=0, writeCount=4911 readSkipCount=0, writeSkipCount=0, processSkipCount=0, commitCount=52, rollbackCount=0, exitDescription=, attributes={}]
11:58:06,823  INFO SimpleAsyncTaskExecutor-54 sink.imagescalertap - XdChunkContextInfo [complete=true, stepExecution=StepExecution: id=11, version=54, name=processImages, status=STARTED, exitStatus=EXECUTING, readCount=4911, filterCount=0, writeCount=4911 readSkipCount=0, writeSkipCount=0, processSkipCount=0, commitCount=53, rollbackCount=0, exitDescription=, attributes={}]
11:58:06,826  INFO SimpleAsyncTaskExecutor-53 sink.imagescalertap - XdChunkContextInfo [complete=true, stepExecution=StepExecution: id=11, version=55, name=processImages, status=STARTED, exitStatus=EXECUTING, readCount=4911, filterCount=0, writeCount=4911 readSkipCount=0, writeSkipCount=0, processSkipCount=0, commitCount=54, rollbackCount=0, exitDescription=, attributes={}]
11:58:06,827  INFO task-scheduler-3 sink.imagescalertap - StepExecution: id=11, version=55, name=processImages, status=COMPLETED, exitStatus=COMPLETED, readCount=4911, filterCount=0, writeCount=4911 readSkipCount=0, writeSkipCount=0, processSkipCount=0, commitCount=54, rollbackCount=0, exitDescription=
11:58:07,148  INFO task-scheduler-3 sink.imagescalertap - JobExecution: id=6, version=1, startTime=Sun Sep 07 11:56:40 EDT 2014, endTime=Sun Sep 07 11:58:07 EDT 2014, lastUpdated=Sun Sep 07 11:56:40 EDT 2014, status=COMPLETED, exitStatus=exitCode=COMPLETED;exitDescription=, job=[JobInstance: id=5, version=0, Job=[imagescaler]], jobParameters=[{}]
11:58:07,148  INFO task-scheduler-3 listener.JobDurationListener - Job took: 1 minutes, 26 seconds.
11:58:07,257  INFO task-scheduler-3 support.SimpleJobLauncher - Job: [FlowJob: [name=imagescaler]] completed with the following parameters: [{}] and the following status: [COMPLETED]
````
