package org.noear.solon.boot.prop;

import org.noear.solon.Solon;
import org.noear.solon.Utils;
import org.noear.solon.boot.ServerConstants;
import org.noear.solon.boot.ServerSignalProps;

/**
 * @author noear
 * @since 1.8
 */
public class HttpSignalProps implements ServerSignalProps {
    private String name;
    private int port;
    private String host;

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public HttpSignalProps() {
        name = Solon.cfg().get(ServerConstants.SERVER_HTTP_NAME);
        port = Solon.cfg().getInt(ServerConstants.SERVER_HTTP_PORT, 0);
        host = Solon.cfg().get(ServerConstants.SERVER_HTTP_HOST);

        if (port < 1) {
            port = Solon.cfg().serverPort();
        }

        if (Utils.isEmpty(host)) {
            host = Solon.cfg().serverHost();
        }
    }
}
