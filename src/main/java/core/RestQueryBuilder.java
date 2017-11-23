package core;

/**
 * A class that contains functions for generating the query reques string for interacting with the Github API
 * @author Brittani
 *
 */
public class RestQueryBuilder {
	
	/**
	 * This function creates the query request string that retrieves data on a Github organization and its corresponding repositories
	 * @param organization - the name of a Github organization
	 * @param numOfRepos - the number of repositories that an organziation has
	 * @return The query string for the organization request
	 */
	protected static String generateOrganizationQuery(String organization, int numOfRepos)
	{
		StringBuilder query = new StringBuilder("query { organization(login: \"")
				.append(organization)
				.append("\") { repositories(first: ")
				.append(numOfRepos)
				.append(") { nodes { name owner { login } } } } }");

		return query.toString();
	}
	
	/**
	 * This function creates the query request string that retrieves the pull requests on a Github repository by its owner and name.
	 * The limit per API request is 100, so pagination is needed. The end cursor tells the request where to start in the list of pull requests, if the repository has more than 100.
	 * @param owner - owner login of the repository
	 * @param name - name of the repository
	 * @param endCursor - the string that identifies the current pull request and its place inside the list of all pull requests
	 * @return The query string for the pull request request
	 */
	protected static String genereatePullRequestsQuery(String owner, String name, String endCursor) {
		
		StringBuilder query = new StringBuilder("query { repository(owner:\"")
				.append(owner)
				.append("\" name:\"")
				.append(name)
				.append("\") { pullRequests(first: 100 after: ");
		
		if(endCursor != null)
		{
			query.append("\"").append(endCursor).append("\" ");
		}
		
		query.append(") { edges { node { author { login } title number createdAt mergedAt } } pageInfo { endCursor hasNextPage } } } }");
				
		return query.toString();
	}
	
}
