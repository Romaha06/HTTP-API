package utils.framework;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import utils.help.Authorization;

public class SuiteListener implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        Authorization.loginToJIRA();
    }

    @Override
    public void onFinish(ISuite suite) {

    }
}
