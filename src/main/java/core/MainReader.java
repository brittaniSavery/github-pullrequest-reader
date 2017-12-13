package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import pojos.PageInfo;
import pojos.PullRequest;
import pojos.PullRequestContainer;
import pojos.PullRequests;
import pojos.RepositoryNode;
import pojos.RequestQuery;
import pojos.ResponseContainer;
import pojos.ResponseError;

public class MainReader {

	static final String ACCESS_TOKEN = ""; //TODO: insert OAuth token
	static final String ORGANIZATION = "lodash";
	static final int NUM_OF_REPOS = 5;

	public static void main(String[] args) {
		
		//Creating client with appropriate header (authorization token)
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://api.github.com").path("graphql");
		Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + ACCESS_TOKEN);

		//Creating the request JSON for organization (getting all repos)
		String gQuery = RestQueryBuilder.generateOrganizationQuery(ORGANIZATION, NUM_OF_REPOS);
		
		//Sending the request
		ResponseContainer reposResponseContainer = sendPost(gQuery, invocationBuilder);
		
		//Checking errors - looping through and ending program if applicable
		List<ResponseError> errors = reposResponseContainer.getErrors();
		if(errors != null)
		{
			for (ResponseError error : errors) {
				System.out.println(error.getMessage());
			}
			return;
		}
		
		//Looping through all repos for organization and then getting pull requests for each
		List<RepositoryNode> repos = reposResponseContainer.getData().getOrganization().getRepositories().getNodes();
		
		List<PullRequest> totalPullRequests = new ArrayList<>();

		//Creating a new calendar to keep count of weeks (in per week)
		Calendar cal = new GregorianCalendar();
		cal.setFirstDayOfWeek(Calendar.FRIDAY);
		cal.setMinimalDaysInFirstWeek(7);
		Map<String,Integer> prsPerWeek = new HashMap<String,Integer>();

		for (RepositoryNode repo : repos) {
			storeAllPullRequestsByRepo(invocationBuilder, totalPullRequests, repo, null);
		}
		
		//Getting pullrequest per week
		for(PullRequest pr : totalPullRequests)
		{
			if(pr.getMergedAt() == null)
				continue;
				
			cal.setTime(pr.getMergedAt());
			String key = cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.WEEK_OF_YEAR);
			
			if(prsPerWeek.containsKey(key))
			{
				int mergedNums = prsPerWeek.get(key).intValue();
				prsPerWeek.put(key,++mergedNums);
			}
			else
				prsPerWeek.put(key,1);
		}
		
		System.out.println("The number of pull requests in the lodash organization is "+totalPullRequests.size());
	}

	/**
	 * A helper function to send a post call to the Github API (Graphql v4)
	 * @param body - the request body, the graphql for the API
	 * @param builder - the request builder with appropriate headers 
	 * @return The java object of the response
	 */
	private static ResponseContainer sendPost(String body,Invocation.Builder builder)
	{
		Gson gson = new Gson();
		RequestQuery requestQuery = new RequestQuery(body);
		String entity = gson.toJson(requestQuery);
		
		Response reposResponse = builder.post(Entity.json(entity));
		ResponseContainer responseContainer = gson.fromJson(reposResponse.readEntity(String.class), ResponseContainer.class);
		
		return responseContainer;
	}
	
	/**
	 * This function will retrieve and store all pull requests of a particular repo. Using the pagination method described from the graphql documentation,
	 * it gets the next set after the current pull requests with the end cursor attribute.
	 * @param builder - the request builder with appropriate headers
	 * @param totalPullRequests - the list of pull requests from all repos, i.e. the storage unit for the pull requests
	 * @param repo - the current repository (<code>RepositoryNode</code>)
	 * @param endCursor - the string that identifies the current pull request and its place inside the list of all pull requests
	 */
	private static void storeAllPullRequestsByRepo(Invocation.Builder builder, List<PullRequest> totalPullRequests, RepositoryNode repo, String endCursor) 
	{
		//Getting pull requests from api
		String prQuery = RestQueryBuilder.genereatePullRequestsQuery(repo.getOwner().getLogin(), repo.getName(), endCursor);
		ResponseContainer prResponseContainer = sendPost(prQuery,builder);
		PullRequests pullRequests = prResponseContainer.getData().getRepository().getPullRequests(); 
		
		//Storing all pull requests
		for (PullRequestContainer prContainer : pullRequests.getEdges()) {
			PullRequest pullrequest = prContainer.getNode();
			totalPullRequests.add(pullrequest);
		}
		
		//Checking to see if pagination is needed to get all pull requests 
		PageInfo pageInfo = pullRequests.getPageInfo();
		if(pageInfo.getHasNextPage())
		{
			//Jump into the function again for pulling next set of pull requests (after current pull request)
			storeAllPullRequestsByRepo(builder, totalPullRequests, repo, pageInfo.getEndCursor());
		}
	}
}
