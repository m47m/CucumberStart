package com.example.cucumberstart.utils;

import java.security.Permission;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 16/03/2023 5:41 pm
 */
public class IgnoreExitCall extends SecurityManager{
    @Override
    public void checkExit(int status) {
       // throw new SecurityException();
    }

    @Override
    public void checkPermission(Permission perm) {
        //Allow other activities by default
    }
}
