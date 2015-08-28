package br.com.faucille;

import br.com.faucille.daemon.Daemon;


/**
 * The start!
 *
 */
public class App {
	public static void main(String[] args) {
		Daemon.getInstance().start();
	}
}
