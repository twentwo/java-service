package org.gearman.examples.echo;

import org.gearman.*;

/**
 * Created by congye on 8/7/2015.
 */
public class TwenWorker implements GearmanFunction {

    /** The echo function name */
    public static final String ECHO_FUNCTION_NAME = "echo";

    /** The host address of the job server */
    public static final String ECHO_HOST = "192.168.56.101";

    /** The port number the job server is listening on */
    public static final int ECHO_PORT = 4730;

    public static void main(String... args) {

        /*
         * Create a Gearman instance
         */
        Gearman gearman = Gearman.createGearman();

        /*
         * Create the job server object. This call creates an object represents
         * a remote job server.
         *
         * Parameter 1: the host address of the job server.
         * Parameter 2: the port number the job server is listening on.
         *
         * A job server receives jobs from clients and distributes them to
         * registered workers.
         */
        GearmanServer server = gearman.createGearmanServer(
                TwenWorker.ECHO_HOST, TwenWorker.ECHO_PORT);

        /*
         * Create a gearman worker. The worker poll jobs from the server and
         * executes the corresponding GearmanFunction
         */
        GearmanWorker worker = gearman.createGearmanWorker();

        /*
         *  Tell the worker how to perform the echo function
         */
        worker.addFunction(TwenWorker.ECHO_FUNCTION_NAME, new TwenWorker());

        /*
         *  Tell the worker that it may communicate with the this job server
         */
        worker.addServer(server);
    }

    @Override
    public byte[] work(String function, byte[] data, GearmanFunctionCallback callback) throws Exception {

        return "twen".getBytes();
    }
}
