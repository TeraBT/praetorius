//package com.bti.configs;
//
//import org.testcontainers.containers.PostgreSQLContainer;
//
//public class FixedPortPostgreSQLContainer extends PostgreSQLContainer<FixedPortPostgreSQLContainer> {
//    public FixedPortPostgreSQLContainer(String dockerImageName) {
//        super(dockerImageName);
//    }
//
//    public void exposeFixedPort(int hostPort, int containerPort) {
//        super.addFixedExposedPort(hostPort, containerPort);
//    }
//}
//
