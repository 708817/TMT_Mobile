/*
DESCRIPTION OF AsyncResponse.java
        Interface ito para makuha ng activities na nirereturn ng WS classes. Since outer class
        yung mga AsyncTasks, imposibleng kunin yung nirereturn nila unless may interface na
        mababasehan.
*/

package com.example.tmt_mobile;

public interface AsyncResponse {
    void processFinish(Object output);
}
