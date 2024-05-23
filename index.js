var gulp = require('gulp');
var reporter = require('cucumber-html-reporter');
var today = new Date();
var date = today.getDate()+'-'+(today.getMonth()+1)+'-'+today.getFullYear();
var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
var reportName = today.getHours()+ "" + today.getMinutes()+ "" + today.getSeconds();
var dateTime = date+' '+time;
var date2=date+' '+reportName;
var folder = date;
const os = require('os');

// Get system details
const platform = os.type() + ' ' + os.release();
const executedLocation = process.env.SSH_CLIENT ? 'Remote System' : 'Local System';


 
var options = {
       theme: 'bootstrap',
       jsonFile: './Reports/cukes/cucumber.json',
      // output: './HtmlReport/'+folder+'/'+date2+'cucumber_report.html',
        output: './AutomationReport/'+'Automation_report.html',
       reportSuiteAsScenarios: true,
       launchReport: true,
       metadata: {
           "Version":"0.0.1",
           "Test Environment":"PRD",
           "Browser"    : "Chrome 87",
           "Author"    : "Nagababu CH",
           "Time"       : dateTime,
           "Platform": platform,
           "Parallel"    : "Scenarios",
           "Executed"    : executedLocation
       }
   };
     reporter.generate(options);