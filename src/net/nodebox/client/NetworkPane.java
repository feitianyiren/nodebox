package net.nodebox.client;

import net.nodebox.node.Network;
import net.nodebox.node.Node;

import java.awt.*;

public class NetworkPane extends Pane {

    private PaneHeader paneHeader;
    private NetworkAddressBar networkAddressBar;
    private NetworkView networkView;
    private Network network;


    public NetworkPane(Document document) {
        this();
        setDocument(document);
    }

    public NetworkPane() {
        setLayout(new BorderLayout(0, 0));
        paneHeader = new PaneHeader(this);
        networkAddressBar = new NetworkAddressBar(this);
        paneHeader.add(networkAddressBar);
        networkView = new NetworkView(this, null);
        add(paneHeader, BorderLayout.NORTH);
        add(networkView, BorderLayout.CENTER);
    }

    @Override
    public void setDocument(Document document) {
        super.setDocument(document);
        if (document == null) return;
        setNetwork(document.getActiveNetwork());
    }

    public Pane clone() {
        return new NetworkPane(getDocument());
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
        networkAddressBar.setNode(network);
        networkView.setNetwork(network);
    }

    @Override
    public void activeNetworkChanged(Network activeNetwork) {
        setNetwork(activeNetwork);
    }

    @Override
    public void activeNodeChanged(Node activeNode) {
        networkView.setHighlight(activeNode);
    }
}