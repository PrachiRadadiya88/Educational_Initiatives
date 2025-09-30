package com.example.patterns.creational.singleton;

public class SingletonDemo {
    public static class GlobalConfig {
        private static final GlobalConfig INSTANCE = new GlobalConfig();
        private GlobalConfig(){}
        public static GlobalConfig getInstance(){ return INSTANCE; }
        private String env = "PROD";
        public String getEnv(){ return env; }
        public void setEnv(String e){ this.env = e; }
    }

    public static void main(String[] args){
        GlobalConfig c1 = GlobalConfig.getInstance();
        System.out.println("Env: " + c1.getEnv());
        c1.setEnv("TEST");
        System.out.println("Env (other ref): " + GlobalConfig.getInstance().getEnv());
    }
}
