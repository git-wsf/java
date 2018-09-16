//package com.test.demo.utils;
////import com.jcraft.jsch.JSch;
////import com.jcraft.jsch.Session;
//
//import java.util.Properties;
//
//public class SSHConnection {
//
//    private final static String S_PATH_FILE_PRIVATE_KEY = "/Users/liuxinyang/.ssh/id_rsa";
//    private final static String S_PATH_FILE_KNOWN_HOSTS = "/Users/liuxinyang/.ssh/known_hosts";
//    private final static String S_PASS_PHRASE = "";
//    private final static int LOCAl_PORT = 3307;
//    private final static int REMOTE_PORT = 3306;
//    private final static int SSH_REMOTE_PORT = 3519;
//    private final static String SSH_USER = "dev";
//    private final static String SSH_PASSWORD = "shj&*nII?22";
//    private final static String SSH_REMOTE_SERVER = "123.56.20.67";
//    private final static String MYSQL_REMOTE_SERVER = "rm-2ze70p2002pay8a45.mysql.rds.aliyuncs.com";
//
////    private Session session; //represents each ssh session
//
//    public void closeSSH ()
//    {
//        session.disconnect();
//    }
//
//    public SSHConnection() throws Throwable
//    {
//
//        JSch jsch = new JSch();
//        //jsch.setKnownHosts(S_PATH_FILE_KNOWN_HOSTS);
//        //jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY);
//
//        session = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);
//
//        session.setPassword(SSH_PASSWORD);
//
//        Properties config = new Properties();
//        config.put("StrictHostKeyChecking", "no");
//        session.setConfig(config);
//
//        session.connect(); //ssh connection established!
//
//        //by security policy, you must connect through a fowarded port
//        session.setPortForwardingL(LOCAl_PORT, MYSQL_REMOTE_SERVER, REMOTE_PORT);
//
//    }
//}
