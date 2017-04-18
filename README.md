# roboscript
RoboScript - run scripts (or any other app) remotely

Deploy this app on your remote server and use its simple rest api to run scripts, stop, check if it's still alive, view logs (even for running processes). 

Supported endpoints:

    /roboscript/startLocalScript?fullPath=
    /roboscript/stopLocalScript?uniqueId=
    /roboscript/showProcessLog?uniqueId=
    /roboscript/isProcessAlive?uniqueId=
    /roboscript/stopAllProcesses
    /roboscript/grantExecutePermissions?fullPath=
    /roboscript/help
