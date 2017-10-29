package sn.projet.hubschool.service.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantUtil {

	@Value("${env}")
	public String env;

	@Value("${aws.s3.hubschool.region}")
	public String s3region;

	@Value("${aws.s3.hubschool.bucketname}")
	public String s3bucketname;

	@Value("${aws.s3.hubschool.event.photo.key}")
	public String s3EventPhotoKey;

}
