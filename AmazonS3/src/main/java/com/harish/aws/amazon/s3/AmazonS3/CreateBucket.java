package com.harish.aws.amazon.s3.AmazonS3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;

public class CreateBucket {

	public static void main(String[] args) {
		Regions clientRegion = Regions.DEFAULT_REGION;
		String bucketName = "myfirsts3bucket-harish";
		final String accessKey = "AKIAYRYBDL57J4BK36ME";
		final String secretKey = "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";
		try{		
		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3 s3Client = AmazonS3ClientBuilder
							.standard()
							.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
							.withRegion(clientRegion)
							.build();
		// Because the CreateBucketRequest object doesn't specify a region, the
        // bucket is created in the region specified in the client.
		if(!s3Client.doesBucketExistV2(bucketName)){
			s3Client.createBucket(new CreateBucketRequest(bucketName));
			
			// Verify that the bucket was created by retrieving it and checking its location.
			String bucketLocation = s3Client.getBucketLocation(new GetBucketLocationRequest(bucketName));
			System.out.println(bucketLocation);
		}
		}catch (AmazonServiceException exe){
			exe.printStackTrace();
		}catch(SdkClientException exe){
			exe.printStackTrace();
		}
	}
}
