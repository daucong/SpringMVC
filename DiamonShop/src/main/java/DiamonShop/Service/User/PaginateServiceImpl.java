package DiamonShop.Service.User;

import org.springframework.stereotype.Service;

import DiamonShop.Dto.PaginatesDto;

@Service
public class PaginateServiceImpl {
	public PaginatesDto GetInfoPaginates(int totalData, int limit, int currentPage) {
		PaginatesDto paginates = new PaginatesDto();

		paginates.setLimit(limit);

		paginates.setTotalPage(SetInfoTotalPage(totalData, limit));
		
		paginates.setCurrentPage(CheckCurrentPage(currentPage,paginates.getTotalPage()));
		
		int start = FindStart(paginates.getCurrentPage(), limit);
		paginates.setStart(start);
		int end = FindEnd(paginates.getStart(), limit, totalData);
		paginates.setEnd(end);
		return paginates;
	}

	private int FindEnd(int start, int limit, int totalData) {
		
		return start + limit > totalData ?  totalData : (start + limit)-1;
	}

	private int FindStart(int currentPage, int limit) {
		
		return ((currentPage - 1) * limit) + 1;
	}

	private int SetInfoTotalPage(int totalData, int limit) {
		int totalPage = 0;
		totalPage = totalData / limit;
		totalPage = totalPage * limit < totalData ? totalPage + 1 : totalPage;
		return totalPage;
	}
	public int CheckCurrentPage(int currentPage, int totalPage) {
		if(currentPage < 1) {
			return 1;
		}
		if(currentPage > totalPage) {
			return totalPage;
		}
		return currentPage;
	}
}
