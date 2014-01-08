package thymeleafexamples.layouts.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.PostConstruct;

public class UserService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostConstruct	
	protected void initialize() {
		accountRepository.save(new Account("user", "demo", "ROLE_USER"));
		accountRepository.save(new Account("admin", "admin", "ROLE_ADMIN"));
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(username);
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}
	
	public void signin(Account account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	private Authentication authenticate(Account account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null, account.getAuthorities());
	}
	
	private User createUser(Account account) {
		return new User(account);
	}

    private static class User extends org.springframework.security.core.userdetails.User {

        private final Account account;

        public User(Account account) {
            super(account.getEmail(), account.getPassword(), account.getAuthorities());
            this.account = account;
        }

        public Account getAccount() {
            return account;
        }

        public boolean isAdmin() {
            return getAccount().isAdmin();
        }
    }

}
