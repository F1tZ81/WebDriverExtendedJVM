package io.swarmauto.driverextended;

/**
 * Created by harolddost on 4/5/16.
 */
public class SafeReport implements Report{

    private Report report;

    private SafeReport(){
        // Requires report to instantiate
    }

    public SafeReport(Report r){
        report = r;
    }

    @Override
    public void prepReporting() {
        if(report != null){
            report.prepReporting();
        }
    }

    @Override
    public void validate(String text, boolean passFail, boolean positiveCheck, byte[] screenShot) {
        if(report != null){
            report.validate(text, passFail, positiveCheck, screenShot);
        }
    }

    @Override
    public void validate(String text, boolean passFail, boolean positiveCheck) {
        if(report != null){
            report.validate(text, passFail, positiveCheck);
        }
    }

    @Override
    public void validate(String text, boolean passFail) {
        if(report != null){
            report.validate(text,passFail);
        }
    }

    @Override
    public void writeStep(String text) {
        if(report != null){
            report.writeStep(text);
        }
    }

    @Override
    public void writeReport() {
        if(report != null){
            report.writeReport();
        }
    }
}
