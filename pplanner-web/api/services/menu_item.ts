import { Repository } from '~/api/Repository'
import { ApiLog } from '~/decorators/ApiLog'

@ApiLog
export class MenuItemsApi extends Repository {
	findById (itemId: string) {
		if (itemId == null) {
			return null
		}
		return this.axios.$get(`/items/${ itemId }`)
	}

	findByIdAndProjectId (itemId: string, projectId: string) {
		if (itemId == null || projectId == null) {
			return null
		}
		return this.axios.$get(`/items/${ itemId }/projects/${ projectId }`)
	}

	createItemByType (type: string, newWorkspace: any) {
		return this.axios.$post(`/items/${ type }`, newWorkspace)
	}
}

