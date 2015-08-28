package br.com.faucille.daemon;

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.faucille.dao.FeederRepository;
import br.com.faucille.model.Feeder;

@DisallowConcurrentExecution
public class UpdateJob implements Job {

	private FeederRepository feederRepository;
	
	public UpdateJob() {
		this.feederRepository = new FeederRepository();
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		int start = 0;
		int size = 50;
		
		while(true) {
			List<Feeder> feeders = feederRepository.getFeeders(start, size);
			if(feeders.size() == 0) break;
			
			start += size + 1;
		}
		
		
		
		System.out.println("teste: " + this.feederRepository.toString());
	}

}
