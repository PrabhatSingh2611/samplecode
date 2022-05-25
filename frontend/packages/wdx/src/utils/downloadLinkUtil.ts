export const createDownloadLink = (url: string): void => {
  const a = document.createElement('a');
  a.style.display = 'none';
  document.body.appendChild(a);
  a.href = url;
  a.setAttribute('download', 'download');
  a.click();
  window.URL.revokeObjectURL(a.href);
  document.body.removeChild(a);
};
