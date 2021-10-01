cd "$(brew --repo)"
git remote set-url origin https://mirrors.ustc.edu.cn/brew.git
# zsh
echo 'export HOMEBREW_BOTTLE_DOMAIN=https://mirrors.ustc.edu.cn/homebrew-bottles' >> ~/.zshrc
source ~/.zshrc
# bash
# echo 'export HOMEBREW_BOTTLE_DOMAIN=https://mirrors.ustc.edu.cn/homebrew-bottles' >> ~/.bash_profile
# source ~/.bash_profile
cd "$(brew --repo)/Library/Taps/homebrew/homebrew-core"
git remote set-url origin https://mirrors.ustc.edu.cn/homebrew-core.git
cd "$(brew --repo)/Library/Taps/homebrew/homebrew-cask"
git remote set-url origin https://mirrors.ustc.edu.cn/homebrew-cask.git
cd "$(brew --repo)/Library/Taps/homebrew/homebrew-cask-versions"
git remote set-url origin https://mirrors.ustc.edu.cn/homebrew-cask-versions.git
brew update --verbose

# 还原
# 手动删除 zshrc 或 bash_profile 里定义的 HOMEBREW_BOTTLE_DOMAIN
# cd "$(brew --repo)"
# git remote set-url origin https://github.com/Homebrew/brew.git
# cd "$(brew --repo)/Library/Taps/homebrew/homebrew-core"
# git remote set-url origin https://github.com/Homebrew/homebrew-core
# cd "$(brew --repo)"/Library/Taps/homebrew/homebrew-cask
# git remote set-url origin https://github.com/Homebrew/homebrew-cask
# cd "$(brew --repo)"/Library/Taps/homebrew/homebrew-cask-versions
# git remote set-url origin https://github.com/Homebrew/homebrew-cask-versions.git
