
package acme.entities.configuration;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpamUtils {

	private static final String	REGEX	= "[\\W]+";

	@Autowired
	private SpamRepository		repository;

	private Spam				spam;

	private static List<String>	spamWords;

	private static Double		count;


	@PostConstruct
	public void init() {
		this.spam = this.repository.findManyAll().iterator().next();
		SpamUtils.count = 0d;
		SpamUtils.spamWords = Arrays.asList(this.spam.getWords().toLowerCase().split(","));
	}

	public Boolean checkSpam(final String text) {
		String[] splitText = text.toLowerCase().split(SpamUtils.REGEX);
		int length = splitText.length;
		Double max = length * (this.spam.getThreshold() / 100);

		Arrays.asList(splitText).parallelStream().forEach(t -> {
			if (SpamUtils.spamWords.contains(t)) {
				SpamUtils.count++;
			}
		});
		Double aux = SpamUtils.count;
		this.resetCount();
		return aux > max;
	}

	private void resetCount() {
		SpamUtils.count = 0d;
	}

	public void setSpam(final Spam entity) {
		this.spam = entity;
		SpamUtils.count = 0d;
		if (this.spam.getWords() != ",") {
			SpamUtils.spamWords = Arrays.asList(this.spam.getWords().split(","));
		}
	}
}
