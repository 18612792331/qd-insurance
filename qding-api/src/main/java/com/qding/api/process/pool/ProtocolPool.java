package com.qding.api.process.pool;

import java.util.HashMap;
import java.util.Map;

import com.qding.api.process.print.AbstractProtocolPrint;

/**
 * 
 * @author lichao
 *
 */
public class ProtocolPool extends AliasPool<AbstractProtocolPrint>{

	private static Map<String, Class<? extends AbstractProtocolPrint>> protocolPool = new HashMap<>();

	private static ProtocolPool self = new ProtocolPool();
	
	private ProtocolPool() {
		super(protocolPool);
	}
	
	public static void mount(String alias, Class<? extends AbstractProtocolPrint> handler) {
		self.register(alias, handler);
	}
	
	public static Class<? extends AbstractProtocolPrint> get(String alias) throws Exception {
		return self.getExecutor(alias);
	}
}
